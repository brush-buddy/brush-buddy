import base64
from io import BytesIO
from typing import Dict, List

import cv2
import db
import numpy as np
import requests
from fastapi import APIRouter, HTTPException, UploadFile
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
def ai_generate(prompt: str):
    user_id = 1
    # prompt -> 이미지 url
    aigenerateimageurl = images.AiImage().createImage(prompt)

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
async def to_pipo(image: UploadFile):
    try:
        # base64 이미지 받아서, numpy array로 변환
        numpy_array = np.array(Image.open(BytesIO(await image.read()))).reshape(
            -1, 349, 3
        )

        json_string_palette, draft_url = drafts.Drafts().pipo_convert(numpy_array)

        return responsePipo.Pipo(image=draft_url, palette=json_string_palette)

    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Error decoding image: {str(e)}")
