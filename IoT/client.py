import asyncio
import websockets
import json
# 라즈베리 파이용 클라이언트

client_id = 2
uri = f"ws://i10a205.p.ssafy.io:3004/ws/{client_id}" 
connected_websocket = ''
timeout = 10
ping_pong_time = 10

async def main():
    async with websockets.connect(uri) as websocket:

        # 연결이 되었으면 웹소켓을 임시로 저장한다.
        connected_websocket = websocket

        try:
            # 연결 유지 로직 10초 마다 핑 날리기
            while True:
                await websocket.send("ping") #Ping
                response = await asyncio.wait_for(websocket.recv(), timeout=timeout)
                print(f"< 서버로 부터 받은 응답: {response}") # Pong
                 
                # 명령을 입력 받는다면 물감 출력을 수행한다
                if response.startswith("CMD"):
                    data = json.loads(response.split(" ", 1)[1]) # Command 뒤에 것을 데이터로 불러온다.
                    print("명령 처리 요청을 받았습니다.")
                    result = await process(data) # 처리 프로세스이다.
                    print("명령을 처리하였습니다.")

                    if result: 
                        print("서버로 처리 성공 메시지 전송...")
                        await websocket.send(f"RESULT{client_id}: Success => {result}")
                    else:
                        print("서버로 처리 실패 메시지 전송...")
                        await websocket.send(f"RESULT{client_id}: Fail => {result}")

                await asyncio.sleep(ping_pong_time) #핑퐁 10초 쉬기

        except KeyboardInterrupt as e:
            print("클라이언트를 종료합니다")
        finally:
            websocket.close()
          
        # 만약 중간에 명령과 데이터가 들어온다면 데이터 추출 후 명령을 수행한다.

# 연결 유지하기 위한 데이터 전송 메커니즘
async def ping(websocket, ping_pong_interval_sec=10, message = "keep going"):
    await websocket.send(message)
    response = await websocket.recv()
    print(f"< 서버로 부터 받은 응답: {response}")
    await asyncio.sleep(ping_pong_interval_sec)


# 명령 처리 프로세스
async def process(data) :
    # 여기서 명령을 수행한다.
    print("명령을 수행하는 중입니다.")
    await asyncio.sleep(5)
    print("명령을 수행완료 했습니다.")
    return True
    

asyncio.run(main())