import io
import os
import time

import boto3
import cv2
import numpy as np
import requests
from dotenv import load_dotenv
from models.awsS3 import AwsS3
from openai import OpenAI
from PIL import Image
from pydantic import BaseModel
from pypipo.libs.utils import *


# ai로 이미지 생성, s3에서 이미지 불러오기, 이미지 해상도 낮추기 함수 존재
class AiImage(BaseModel):
    # 프롬프트 받으면 생성형 ai 로 이미지 url(string)만들어주는 함수
    def createImage(self, prompt: str) -> str:
        client = OpenAI(api_key="*")

        response = client.images.generate(
            model="dall-e-2",
            prompt=prompt,
            size="256x256",
            quality="standard",
            n=1,
        )

        # 생성형 ai에서 이미지 url 받아오기
        image_url = response.data[0].url

        # # aws s3 연결
        # s3 = aws.s3_connection()

        # # numpy image 코드 'colored_draft.png' 로 저장.
        # cv2.imwrite("created_colored_image.png", downgrade_image)

        # file_name = "created_colored_image.png"  # 업로드할 파일 이름
        # bucket = "brushbuddy0"  # 버켓 주소
        # key = "created_colored_image.PNG"  # s3  내부 이미지 파일 이름

        # s3.upload_file(file_name, bucket, key)  # aws s3에 색칠된 도안 "colored_draft.PNG"로 저장

        return image_url

    # S3에서 이미지 불러오는 함수
    def get_image():
        # os 환경변수 불러오기
        load_dotenv(verbose=True)

        # 환경변수 불러오기
        AWS_ACCESS_KEY_ID = os.getenv("AWS_ACCESS_KEY_ID")
        AWS_SECRET_ACCESS_KEY = os.getenv("AWS_SECRET_ACCESS_KEY")

        # S3 클라이언트 생성
        s3 = boto3.client(
            "s3",
            aws_access_key_id=AWS_ACCESS_KEY_ID,
            aws_secret_access_key=AWS_SECRET_ACCESS_KEY,
        )

        file_name = "created_colored_image.png"  # 다운로드할 파일 이름
        bucket = "brushbuddy0"  # 버켓 주소
        key = "created_colored_image.png"  # s3  내부 이미지 파일 이름

        # S3에서 이미지 다운로드
        s3.download_file(bucket, key, file_name)
        print(f"Image downloaded from S3: {file_name}")

        #
        return "https://brushbuddy0.s3.ap-northeast-2.amazonaws.com/created_colored_image.png"

    def base64_to_npimage(base64_string: str) -> np.ndarray:
        # base64 -> image numpy array
        img = Image.open(io.BytesIO(base64_string))
        np_img = np.array(img)

        return np_img

    # base64 넣으면, 해상도 낮춘 numpy 데이터 out
    def downSize(base64image: str) -> np.ndarray:
        # base64 -> PIL 파일
        img_out = Image.open(io.BytesIO(base64image))

        # 화질 낮추기 전 이미지 해상도 확인 코드
        # width, height = image.size
        # print(f"{width}: 너비 pixel , {height}: 높이 pixel")

        # 화질 낮추기
        downgrade_image = img_out.resize((500, 500))

        # 화질 낮춘 후 이미지 해상도 확인 코드
        # after_width, after_height = downgrade_image.size
        # print(f"{after_width}: 너비 pixel , {after_height}: 높이 pixel")

        # 화질 낮춘 PIL 파일을 numpy배열로 바꾸는 코드
        downgrade_nparray_image = np.array(downgrade_image)

        return downgrade_nparray_image
