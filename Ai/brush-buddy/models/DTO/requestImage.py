from pydantic import BaseModel


class Ba64(BaseModel):
    b64_image: str
