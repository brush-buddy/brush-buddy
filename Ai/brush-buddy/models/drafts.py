import json
import math
import time
from io import BytesIO

import cv2
import numpy as np
import requests
from models.awsS3 import AwsS3
from models.image_ai import AiImage
from openai import OpenAI
from PIL import Image
from pydantic import BaseModel
from pypipo.convert import pipo_convert
from pypipo.libs.process import ColorspaceIndexing, LineDrawing, Painting
from pypipo.libs.utils import *


class Draft(BaseModel):
    # 생성형 ai로 만들어진 url받아서 pipo 도안화 하는 함수
    # 실행시 aws s3에 clustering(색칠된 도안) image와 numbering된 도안을 저장함.
    # 이미지 이름이..고정되어있음 리팩토링해야함.
    # 팔레트 16가지 색깔 default로 hex 코드 json으로 생성 => 추후에 db에 저장해야함.
    def pipo_convert(np_image, color_label=False, **kwargs):
        aws = AwsS3()
        ai = AiImage()

        # aws s3 연결
        s3 = aws.s3_connection()

        # 피포 페인팅화 시작 ============================================
        painting = Painting(np_image)

        # painting_img : clustering된 이미지(default = 16)
        # color_index_map : 1-16 까지
        painting_img, color_index_map = painting.run(**kwargs)
        color_indexs, color_rbg_values = painting.get_clustered_color_info(painting_img)

        # tensor로 된 색칠된 이미지를 'colored.png' 라는 이미지로 변환 colored => True, False반환
        is_saved = cv2.imwrite("colored_draft.png", painting_img)

        # print(f'색칠된 이미지 저장 잘 됐는지? : {is_saved}')

        file_name = "colored_draft.png"  # 업로드할 파일 이름
        bucket = "brushbuddy0"  # 버켓 주소
        key = "colored_draft.PNG"  # s3  내부 이미지 파일 이름

        s3.upload_file(file_name, bucket, key)  # aws s3에 색칠된 도안 "colored_draft.PNG"로 저장
        # ===============================================================

        # 팔레트 json 정보 저장 =========================================

        # key값 = index, value 값 = 색깔 hex 코드의 dictionary(색깔 총 16개)

        hexarray = dict()

        # 16개 컬러 to hex
        # key = 번호, value = hexcode 로 되어있는 dictionary생성
        for idx, rbg in enumerate(color_rbg_values):
            hexcode = bgr_to_hex(rbg)
            print(f"{idx} idx,{hexcode} hex_code")
            hexarray[str(idx + 1)] = hexcode

        # 확인 코드
        # print(hexarray)

        # json화 된 팔레트정보 ==> 나중에 db에 넣으면 됨.
        hexcode_json_string = json.dumps(hexarray)
        # print(hexcode_json_string,'hexcode_json')

        # ===============================================================

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
        output = numbering.run(img_lab, lab, color_label=color_label)

        # np.array 형태의 도안을 numbering_draft.PNG에 저장
        cv2.imwrite("numbering_draft.PNG", output)

        # time stamp
        t_stamp = time.strftime("%Y%m%d%H%", time.localtime())

        numbering_file_name = f"numbering_draft{t_stamp}.PNG"  # 업로드할 파일 이름
        numbered_key = f"numbering_draft{t_stamp}.PNG"  # s3  내부 이미지 파일 이름

        s3.upload_file(
            numbering_file_name, bucket, numbered_key
        )  # aws s3에 numbering 된 도안 파일 "numbering_draft202301301400.PNG"로 저장

        # return palette, numbered_draft_url
        return (
            hexarray,
            f"https://brushbuddy0.s3.ap-northeast-2.amazonaws.com/{numbering_file_name}",
        )

    class Config:
        schema_extra = {
            "example": {
                "numbering_draft": "https://brushbuddy0.s3.ap-northeast-2.amazonaws.com/numbering_draft2301301400.PNG",
                "palette": {
                    "1": "1F1616",
                    "2": "373234",
                    "3": "4A4B4F",
                    "4": "595D64",
                    "5": "7F8A92",
                    "6": "BDC7CD",
                    "7": "D4DCE0",
                    "8": "AAB5BE",
                    "9": "96A2AA",
                    "10": "A5948D",
                    "11": "8A7672",
                    "12": "7B5B57",
                    "13": "6A7179",
                    "14": "EEF5F7",
                    "15": "266291",
                    "16": "2CA0D1",
                },
            }
        }
