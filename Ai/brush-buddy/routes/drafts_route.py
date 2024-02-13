import base64
import io
import os
import time
import uuid
from io import BytesIO
from typing import Dict, List

import cv2
import db
import numpy as np
import redis
import requests
from fastapi import APIRouter, File, HTTPException, UploadFile
from models import drafts, images
from models.DTO import requestPrompt, requestUrl, responseImage, responsePipo
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
async def ai_generate(prompt: requestPrompt.Prompt, user_id: int = 1):
    user = user_id
    # prompt -> 이미지 url
    simplePrompt = "따라그리기 중간정도 난이도로 "
    aigenerateimageurl = images.AiImage().createImage(simplePrompt + prompt.prompt)
    print(aigenerateimageurl)

    r = redis.StrictRedis(host="i10a205.p.ssafy.io", port=6379, db=0)

    r.incr(user, 1)
    print(r.get(user), "callnum")  # callnum 확인용
    call_num = r.get(user)

    # cnt = db.redis.save_callnum(user)

    if int(call_num) > 20:
        return JSONResponse(
            status_code=status.HTTP_429_TOO_MANY_REQUESTS,
            content={"error": "Too Many Requests"},
        )
    else:
        return responseImage.Img_url(image_url=aigenerateimageurl)


# base64 이미지 받아서, 팔레트 json 데이터 return 하는 api
@draft_router.post("/pipo-local", status_code=200, response_model=responsePipo.Pipo)
async def to_pipo_savelocal(image: UploadFile = File(...)):
    # try:
    UPLOAD_DIR = "./assets"  # 이미지를 저장할 서버 경로

    content = await image.read()
    filename = "image.jpg"
    with open(os.path.join(UPLOAD_DIR, filename), "wb") as fp:
        fp.write(content)  # 서버 로컬 스토리지에 이미지 저장 (쓰기)

    content = cv2.imread("./assets/image.jpg")

    json_string_palette, colored_draft_url, numbered_draft_url = (
        drafts.Drafts().pipo_convert(content)
    )

    return responsePipo.Pipo(
        number_image=numbered_draft_url,
        color_image=colored_draft_url,
        palette=json_string_palette,
    )


@draft_router.post("/pipo-s3", status_code=200, response_model=responsePipo.Pipo)
async def to_pipo_saves3(url: requestUrl.Url):
    response = requests.get(url.url)
    img = Image.open(BytesIO(response.content))
    content = np.array(img)

    json_string_palette, colored_draft_url, numbered_draft_url = (
        drafts.Drafts().pipo_convert(content)
    )

    return responsePipo.Pipo(
        number_image=numbered_draft_url,
        color_image=colored_draft_url,
        palette=json_string_palette,
    )
