from pydantic import BaseModel


class Redis(BaseModel):
    left_cnt: int
