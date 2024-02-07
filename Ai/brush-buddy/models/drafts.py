import json
import time
from io import BytesIO

import cv2
import numpy as np
import requests
from models.awsS3 import AwsS3
from models.images import AiImage
from openai import OpenAI
from PIL import Image
from pydantic import BaseModel
from pypipo.convert import pipo_convert
from pypipo.libs.process import ColorspaceIndexing, LineDrawing, Painting
from pypipo.libs.utils import *


class Drafts(BaseModel):

    def pipo_convert(self, np_image, color_label=False, **kwargs):

        # time stamp
        t_stamp = time.strftime("%Y%m%d%H", time.localtime())

        aws = AwsS3()
        aiImg = AiImage()

        # aws s3 연결
        s3 = aws.s3_connection()

        # 입력 이미지 데이터 타입 확인 및 변환
        if np_image.dtype != "uint8":  # 이미지 데이터 타입이 'uint8'이 아닌 경우
            np_image = np_image.astype("uint8")  # 데이터 타입을 'uint8'로 변환

        # 피포 페인팅화 시작 ============================================
        painting = Painting(np_image)

        # painting_img : clustering된 이미지(default = 16)
        # color_index_map : 1-16 까지
        painting_img, color_index_map = painting.run(**kwargs)
        color_indexs, color_rbg_values = painting.get_clustered_color_info(painting_img)

        # s3에 색칠된 도안 업로드 ========================================
        file_name = f"colored_draft_{t_stamp}.png"  # 업로드할 파일 이름
        bucket_name = "brushbuddy0"  # 버켓 주소
        key = file_name  # s3  내부 이미지 파일 이름

        colored_file_path = f"./colored_img/{file_name}"  # 업로드할 파일 이름

        cv2.imwrite(colored_file_path, painting_img)

        # aws s3에 색칠된 도안 "colored_draft_YYYYMMDDHH.png"로 저장
        try:
            s3.upload_file(file_name, bucket_name, key)
        except Exception as e:
            print(e)

        # 팔레트 json 정보 저장 =========================================

        # key값 = index, value 값 = 색깔 hex 코드의 dictionary(색깔 총 16개)

        hexarray = dict()

        # 16개 컬러 to hex
        # key = 번호, value = hexcode 로 되어있는 dictionary생성
        for idx, rbg in enumerate(color_rbg_values):
            hexcode = aiImg.bgr_to_hex(rbg)
            print(f"{idx} idx,{hexcode} hex_code")
            hexarray[f"{idx + 1}"] = hexcode

        # # 확인 코드
        # print(hexarray)

        # json화 된 팔레트정보
        hexcode_json_string = json.dumps(hexarray)
        # print(hexcode_json_string, "hexcode_json")

        # numbering 된 도안 생성 시작 ==========================================
        drawing = LineDrawing(color_index_map)

        # line_drawn_image : 라인 그려진 이미지 (numpy.array 형태)
        line_drawn_image = drawing.run(outline=True)

        # TODO: change values name : img_lab, lab
        img_lab, lab = drawing.get_image_lab(color_rbg_values, painting_img)

        numbering = ColorspaceIndexing(
            painting_img, line_drawn_image, color_indexs, color_rbg_values
        )

        # output : numbering 된 np.array 형태의 도안
        numbering_img = numbering.run(img_lab, lab, color_label=color_label)

        numbering_file_path = (
            f"./numbering_img/numbering_draft_{t_stamp}.PNG"  # 업로드할 파일 이름
        )

        numbering_file_name = f"numbering_draft_{t_stamp}.PNG"  # 업로드할 파일 이름

        cv2.imwrite(numbering_file_path, numbering_img)

        # s3에 numbering 된 도안 업로드 ========================================

        numbered_key = numbering_file_name  # s3  내부 이미지 파일 이름

        # aws s3에 색칠된 도안 "colored_draft_YYYYMMDDHH.png"로 저장
        try:
            s3.upload_file(numbering_file_name, bucket_name, numbered_key)
        except Exception as e:
            print(e)

        # return palette, numbered_draft_url

        numbered_draft_url = (
            f"https://brushbuddy0.s3.ap-northeast-2.amazonaws.com/{numbering_file_name}"
        )

        return hexcode_json_string, numbered_draft_url
