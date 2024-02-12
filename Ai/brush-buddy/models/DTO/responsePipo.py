from pydantic import BaseModel


class Pipo(BaseModel):
    number_image: str
    color_image: str
    palette: str
