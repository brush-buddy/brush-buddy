import base64
import io
import os
import time
from io import BytesIO
from typing import Dict, List

import cv2
import db
import numpy as np
import requests
from fastapi import APIRouter, File, HTTPException, UploadFile
from models import drafts, images
from models.DTO import requestPrompt, responseImage, responsePipo
from PIL import Image
from starlette import status
from starlette.responses import JSONResponse

draft_router = APIRouter(
    tags=["Draft"],
)


# 프롬프트 받아서, ai호출해서 이미지 url return 하는 api
@draft_router.post(
    "/ai-generation", status_code=200, response_model=responseImage.Img_url
)
async def ai_generate(prompt: requestPrompt.Prompt):
    user_id = 1
    # prompt -> 이미지 url
    aigenerateimageurl = images.AiImage().createImage(prompt.prompt)

    # cnt = db.redis.save_callnum(user_id)
    # if int(cnt) > 20:
    #     return JSONResponse(
    #         status_code=status.HTTP_429_TOO_MANY_REQUESTS,
    #         content={"error": "Too Many Requests"},
    #     )
    # else:
    #     return responseImage.Img_url(image_url=aigenerateimageurl)

    return responseImage.Img_url(image_url=aigenerateimageurl)


# base64 이미지 받아서, 팔레트 json 데이터 return 하는 api
@draft_router.post("/pipo-painting", status_code=200, response_model=responsePipo.Pipo)
async def to_pipo(image: UploadFile = File(...)):
    # try:
    UPLOAD_DIR = "./assets"  # 이미지를 저장할 서버 경로

    content = await image.read()
    filename = "image.jpg"
    with open(os.path.join(UPLOAD_DIR, filename), "wb") as fp:
        fp.write(content)  # 서버 로컬 스토리지에 이미지 저장 (쓰기)

    # # 이미지 파일 열기
    # timage = Image.open("./assets/image.jpg")
    # print("-===========================")
    # print(timage.size)
    # 이미지를 NumPy 배열로 변환
    # numpy_array = np.array(timage)

    with open(os.path.join(UPLOAD_DIR, filename), "rb") as fp:
        content = fp.read()

    src_img = cv2.imread("./assets/image.jpg")

    # Image.open(BytesIO(await image.read())).save("test.png")

    # img = cv2.imdecode(
    #     (np.fromstring(BytesIO(await image.read()), dtype=np.uint8)),
    #     cv2.IMREAD_COLOR,
    # )
    # print(img.shape)

    # a = io.BytesIO(await image.read())
    # Image.open(a).save("test1.png")
    # b = np.fromstring(a, dtype=np.uint8)
    # c = cv2.imdecode(b)
    # print(c.shape)

    # img_file = np.fromstring(image.read(),np.uint8)
    # img = cv2.imdecode(img_file,cv2.IMREAD_COLOR)

    # base64 이미지 받아서, numpy array로 변환
    # numpy_array = np.array(Image.open(BytesIO(await image.read()))).reshape(-1, 349, 3)
    # cv2.imwrite("test.png", numpy_array)

    json_string_palette, draft_url = drafts.Drafts().pipo_convert(src_img)

    return responsePipo.Pipo(image=draft_url, palette=json_string_palette)
    # return "ok"

    # except Exception as e:
    #     raise HTTPException(status_code=400, detail=f"Error decoding image: {str(e)}")
