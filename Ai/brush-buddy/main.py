import os

import uvicorn
from fastapi import FastAPI
from routes.drafts_route import draft_router
from starlette.middleware.cors import CORSMiddleware

app = FastAPI()

app.include_router(draft_router, prefix="/api/v1/draft")

# run : python main.py


@app.get("/")
def index():
    return {
        "PYthon": "Framework",
    }


app.add_middleware(
    CORSMiddleware,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

if __name__ == "__main__":
    uvicorn.run("main:app", host="0.0.0.0", port=8000, reload=True)
