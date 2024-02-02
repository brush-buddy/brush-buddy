from typing import Dict

from pydantic import BaseModel


class Pipo(BaseModel):
    image: str
    palette: Dict
