import asyncio
import websockets


async def send_command_and_receive_status():
    command_uri = "ws://127.0.0.1:8000/ws/command/raspberry_pi_1"
    status_uri = "ws://127.0.0.1:8000/ws/status/raspberry_pi_1"

    # 명령 전송을 위한 웹소켓 연결
    async with websockets.connect(command_uri) as websocket:
        await websocket.send("Turn on LED")
        command_response = await websocket.recv()
        print(f"< Command Response: {command_response}")

    # 상태 정보 수신을 위한 별도의 웹소켓 연결
    async with websockets.connect(status_uri) as websocket:
        status_response = await websocket.recv()
        print(f"< Status Update: {status_response}")


asyncio.run(send_command_and_receive_status())