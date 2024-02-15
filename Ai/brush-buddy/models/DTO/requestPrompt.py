from pydantic import BaseModel


class Prompt(BaseModel):
    user_id: int
    prompt: str
