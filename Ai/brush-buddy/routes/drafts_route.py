import base64
import io
import os
import time
import urllib.request
import uuid
from io import BytesIO
from typing import Dict, List

import cv2
import numpy as np
import redis
import requests
from fastapi import APIRouter, File, UploadFile
from models import drafts, images
from models.DTO import (
    requestPrompt,
    requestUrl,
    requestUser,
    responseImage,
    responsePipo,
    responseRedis,
)
from PIL import Image
from starlette import status
from starlette.responses import JSONResponse

draft_router = APIRouter(
    tags=["Draft"],
)


# ai 생성 화면 들어갔을 때 호출 횟수 확인용 api
@draft_router.get("/get_cnt", status_code=200, response_model=responseRedis.Redis)
async def redis_cnt(user_id: int):

    r = redis.StrictRedis(host="i10a205.p.ssafy.io", port=6379, db=0)

    # 초기화
    if r.get(user_id) is None:
        r.set(user_id, 0)

    call_num = r.get(user_id)
    # print(call_num, "번 호출")

    return responseRedis.Redis(left_cnt=20 - int(call_num))


# 프롬프트 받아서, ai호출해서 이미지 url return 하는 api
@draft_router.post(
    "/ai-generation", status_code=200, response_model=responseImage.Img_url
)
async def ai_generate(resBody: requestPrompt.Prompt):

    r = redis.StrictRedis(host="i10a205.p.ssafy.io", port=6379, db=0)

    user = resBody.user_id

    if r.get(resBody.user_id) is None:
        r.set(resBody.user_id, 0)

    # if resBody.prompt == "" or resBody.prompt == '""':
    #     return responseImage.Img_url(image_url="", left_cnt=20 - int(call_num))

    # prompt -> 이미지 url
    simplePrompt = "Super Simple"
    aigenerateimageurl = images.AiImage().createImage(simplePrompt + resBody.prompt)

    # print(aigenerateimageurl)

    # cnt = db.redis.save_callnum(user)

    cnt = r.get(user)

    if int(cnt) >= 20:
        return JSONResponse(
            status_code=status.HTTP_429_TOO_MANY_REQUESTS,
            content={"error": "Too Many Requests"},
        )
    else:

        r.incr(user, 1)

        call_num = r.get(user)

        return responseImage.Img_url(
            image_url=aigenerateimageurl, left_cnt=20 - int(call_num)
        )


# base64 이미지 받아서, 팔레트 json 데이터 return 하는 api
@draft_router.post("/pipo-local", status_code=200, response_model=responsePipo.Pipo)
async def to_pipo_savelocal(image: UploadFile = File(...)):

    uuid_val = uuid.uuid1()

    content = await image.read()
    filename = f"{uuid_val}.jpg"
    with open(filename, "wb") as fp:
        fp.write(content)  # 서버 로컬 스토리지에 이미지 저장 (쓰기)

    content = cv2.imread(filename)

    json_string_palette, colored_draft_url, numbered_draft_url = (
        drafts.Drafts().pipo_convert(content)
    )

    os.remove(filename)

    return responsePipo.Pipo(
        number_image=numbered_draft_url,
        color_image=colored_draft_url,
        palette=json_string_palette,
    )


@draft_router.post("/pipo-s3", status_code=200, response_model=responsePipo.Pipo)
async def to_pipo_saves3(url: requestUrl.Url):

    uuid_val = uuid.uuid1()
    img_dest = f"{uuid_val}.png"

    urllib.request.urlretrieve(url.url, img_dest)

    content = cv2.imread(img_dest)

    json_string_palette, colored_draft_url, numbered_draft_url = (
        drafts.Drafts().pipo_convert(content)
    )

    os.remove(img_dest)

    return responsePipo.Pipo(
        number_image=numbered_draft_url,
        color_image=colored_draft_url,
        palette=json_string_palette,
    )
