from fastapi import FastAPI, WebSocket
import asyncio
from device import devices  
import uvicorn
from typing import Dict
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()
origins = [
    "http://localhost",
    "http://localhost:8080",
    "https://bb-back.duckdns.org",
    "https://brush-buddy.duckdns.org"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

connected_clients: Dict[str, WebSocket] = {}
# message_from_client: Dict[str, str]  = {}
semaphore = asyncio.Semaphore(value=1)
timeout = 5;

# 클라이언트가 WebSocket으로 연결될 때 호출되는 핸들러
@app.websocket("/ws/{client_id}")
async def websocket_endpoint(websocket: WebSocket, client_id : str):
    await websocket.accept()

    # 연결된 클라이언트가 있는지 확인한다.
    if connected_clients.get(client_id): # 이미 저장된 값이 있다면
        await websocket.send_text("이미 연결 중인 클라이언트 ID 입니다")
        await websocket.close()
        return # 연결 취소
    
    # 없으면 클라이언트 정보 저장
    connected_clients[client_id] = websocket

    try:
        # 클라이언트와 연결 유지를 진행한다.
        # 서버가 클라이언트에게 핑을 날리고 다시 받는다.
        while True:
            async with semaphore:
                await websocket.send_text(f"ping")
                data = await asyncio.wait_for(websocket.receive_text(),timeout=timeout) # 핑 보내고 5초대기
            print(f"client {client_id} said : {data}")
            await asyncio.sleep(5)

    # except WebSocketDisconnect:
    #     await websocket.close()
    finally:
        del connected_clients[client_id]
        await websocket.close()

    
#스프링 서버가 클라이언트에게 명령을 하달하는 메소드
@app.websocket("/ws/print/{client_id}")
async def print_palette(
    websocket:WebSocket,
    client_id: str
    ):

    # 스프링 서버와의 연결을 승인
    await websocket.accept()

    print("스프링 서버로 부터 요청을 기다리고 있습니다...")
    response = await asyncio.wait_for(websocket.receive_text(),timeout=10)
    print(f"스프링 서버로 부터 요청 : {response}")

    # 연결된 클라이언트가 있는지 확인한다.
    if connected_clients.get(client_id): #연결이 되어있다면
        client_socket = connected_clients[client_id]

        # 명령을 클라이언트에 전달한다.
        await client_socket.send_text(f"{response}")

        # 클라이언트로부터 받은 데이터를
        result = ''
        async with semaphore:
            print(f"I'm wating response from client {client_id}...")
            result = await client_socket.receive_text()

        if result:
            print("전달 이후에 명령까지 성공")
            await websocket.send_text(f"명령 성공 결과 : {result}")
        else:
            print("명령 실행 실패")
            await websocket.send_text(f"명령 실패 결과 : {result}")

    else:
        await websocket.send_text("해당 클라이언트 ID는 연결되어 있지 않습니다.")
        websocket.close()


if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=3004,  ws_ping_interval=300, ws_ping_timeout=300)
