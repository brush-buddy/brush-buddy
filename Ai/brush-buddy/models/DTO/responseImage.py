from pydantic import BaseModel


class Img(BaseModel):
    image_url: str
