import asyncio
import websockets
import json
# 라즈베리 파이용 클라이언트

device_id = 2
uri = f"ws://i10a205.p.ssafy.io:3004/ws/print/{device_id}" 
# uri = f"ws://localhost:3004/ws/print/{device_id}" 

connected_websocket = ''
timeout = 10

# 전송해야할 데이터 형식
data = {
    "id" : device_id,
    "color" : { #cymkw
        'c': 10,
        'y': 20,
        'm': 0,
        'k': 20,
        'w': 10
    }
}

async def main(): 
    async with websockets.connect(uri) as websocket:
        try:
            await websocket.send(f"CMD {json.dumps(data)}") #Ping

            response = await websocket.recv() # 결과가 나올때까지 기다린다.

            print(f"< 서버로 부터 받은 응답: {response}") # Pong

        except KeyboardInterrupt as e:
            print("클라이언트를 종료합니다")
        finally:
            websocket.close()

asyncio.run(main())