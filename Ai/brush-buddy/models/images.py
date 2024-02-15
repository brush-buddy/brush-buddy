import base64
import io
import os
import uuid

import numpy as np
from models.awsS3 import AwsS3
from openai import OpenAI
from PIL import Image, ImageDraw, ImageFont
from pydantic import BaseModel
from pypipo.libs.utils import *


class AiImage(BaseModel):
    # 프롬프트 받으면 생성형 ai 로 이미지 url(string)만들어주는 함수
    def createImage(self, prompt: str) -> str:

        # 환경변수 불러오기
        # OPEN_AI_KEY = os.getenv("OPEN_AI_KEY")
        client = OpenAI(api_key="*")

        response = client.images.generate(
            model="dall-e-3",
            prompt=prompt,
            size="1024x1024",
            quality="standard",
            n=1,
        )

        # 생성형 ai에서 이미지 url 받아오기
        image_url = response.data[0].url

        return image_url

    def base64_to_numpy(self, base64_string):
        try:
            # Base64 디코딩
            image_data = base64.b64decode(base64_string)

            # BytesIO를 사용하여 이미지 데이터를 메모리에 로드
            image_stream = io.BytesIO(image_data)

            # Pillow를 사용하여 이미지 열기
            image = Image.open(image_stream)

            # 이미지를 NumPy 배열로 변환
            numpy_array = np.array(image)
            return numpy_array

        except Exception as e:
            print(f"Error decoding or converting to image: {str(e)}")
            return None

    def bgr_to_hex(self, rgb):
        b, g, r = rgb

        # BGR 값을 16진수로 변환하여 반환
        hex_code = "#{:02x}{:02x}{:02x}".format(r, g, b)
        return hex_code

    def add_watermark(self, image_path):
        input_image = Image.open(image_path)
        watermark_text = "BRUSH BUDDY"

        draw = ImageDraw.Draw(input_image)
        width, height = input_image.size

        print(width, height)

        font = ImageFont.load_default(size=32)  # 워터마크에 사용할 폰트 및 크기 설정

        (left, top, right, bottom) = font.getbbox(watermark_text)

        x = (width - right + left) / 2
        y = (height - bottom + top) / 2

        # 텍스트를 이미지에 추가
        draw.text(
            (x, y),
            watermark_text,
            font=font,
            fill=(128, 128, 128, 0),
        )

        # draw.text(
        #     (width / 3, height / 3),
        #     watermark_text,
        #     font=font,
        #     fill=(128, 128, 128, 10),
        # )

        # draw.text(
        #     (x / 1.5, y / 1.5),
        #     watermark_text,
        #     font=font,
        #     fill=(128, 128, 128, 10),
        # )

        # draw.text(
        #     (x * 4, y * 4),
        #     watermark_text,
        #     font=font,
        #     fill=(128, 128, 128, 50),
        # )

        # draw.text(
        #     (x // 3, y // 3),
        #     watermark_text,
        #     font=font,
        #     fill=(128, 128, 128, 50),
        # )

        # draw.text(
        #     (x, y),
        #     watermark_text,
        #     font=font,
        #     fill=(128, 128, 128, 100),
        # )
        # upload_numpy_array_to_s3(bucket_name, file_key, numpy_array, content_type)
        # upload_Pillow_image_to_s3(bucket_name, file_key, pillow_image, content_type)

        aws = AwsS3()
        s3 = aws.s3_connection()
        uuid_val = uuid.uuid1()
        file_name = f"watermark_{uuid_val}.png"

        key = f"draft/watermark/{file_name}"

        # if not os.path.exists("watermark"):
        #     os.makedirs("watermark")

        # 로컬 디렉토리에 이미지 저장
        input_image.save(file_name)

        s3.upload_file(file_name, "brush-buddy", key)

        # 워터 마크 로컬 파일 지우기
        # os.remove(file_name)

        return f"https://brush-buddy.s3.ap-northeast-2.amazonaws.com/{key}"
