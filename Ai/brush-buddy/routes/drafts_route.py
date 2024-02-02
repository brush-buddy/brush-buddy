import io
import os
from typing import Dict

import numpy as np
from fastapi import APIRouter, HTTPException, dependencies, status
from fastapi.responses import JSONResponse
from models import drafts, image_ai
from models.DTO import requestPrompt, responseImage, responsePipo
from PIL import Image
from pydantic import BaseModel
from sqlalchemy.orm import Session

# from db.connection import get_db
# from db.models.drafts import Draft

# from apis import image


draft_router = APIRouter(
    tags=["Draft"],
)


# 프롬프트 받아서, ai호출해서 이미지 url return 하는 api
@draft_router.post("/ai-generation", status_code=200)
def ai_generate(prompt: requestPrompt.Prompt):
    # prompt -> 이미지 url
    print(prompt)
    aigenerateimageurl = image_ai.AiImage().createImage(prompt.prompt)
    img_url_response = responseImage.Img(image_url=aigenerateimageurl)
    return img_url_response


# base64 이미지 받아서, 팔레트 json 데이터 return 하는 api
@draft_router.post("/pipo-painting", status_code=200, response_model=responsePipo.Pipo)
def to_pipo(inputbase64: str):
    # base64 이미지 downsize
    input_np_Img = image_ai.AiImage().downSize(inputbase64)

    # downsize된 np이미지를 pipo_convert 함수에 넣어서, 팔레트 json 데이터 생성
    json_palette, draft_url = drafts.Draft().pipo_convert(input_np_Img)

    return responsePipo.Pipo(image=draft_url, palette=json_palette)
