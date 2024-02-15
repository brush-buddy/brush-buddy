import os

import uvicorn
from fastapi import FastAPI
from routes.drafts_route import draft_router
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

app.include_router(draft_router, prefix="/api/v1/draft")
# ============= CORS =============

origins = [
    "http://localhost",
    "http://localhost:8000",
    "http://localhost:3000",
    "http://localhost:8080",
    "http://localhost:5173",
    "http://localhost:5174",
    "https://localhost",
    "https://brush-buddy.duckdns.org",
    "https://brush-buddy.duckdns.org/",
    "https://bb-back.duckdns.org/",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# run : python main.py


@app.get("/")
def index():
    return {
        "Python": "Framework",
    }


if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
