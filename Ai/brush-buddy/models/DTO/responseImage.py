from pydantic import BaseModel


class Img_url(BaseModel):
    image_url: str
    left_cnt: int
