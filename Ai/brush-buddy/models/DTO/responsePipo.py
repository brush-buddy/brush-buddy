from pydantic import BaseModel


class Pipo(BaseModel):
    image: str
    palette: str
