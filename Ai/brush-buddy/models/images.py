import base64
import io
import os

import numpy as np
from dotenv import load_dotenv
from openai import OpenAI
from PIL import Image
from pydantic import BaseModel
from pypipo.libs.utils import *


class AiImage(BaseModel):
    # 프롬프트 받으면 생성형 ai 로 이미지 url(string)만들어주는 함수
    def createImage(self, prompt: str) -> str:

        # openai api key
        load_dotenv(verbose=True)

        # 환경변수 불러오기
        # OPEN_AI_KEY = os.getenv("OPEN_AI_KEY")
        client = OpenAI(api_key="*")

        response = client.images.generate(
            model="dall-e-3",
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
