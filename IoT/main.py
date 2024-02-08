from fastapi import FastAPI, WebSocket
import asyncio
import asyncssh
from device import devices  
import json

app = FastAPI()

async def runSshCommand(ipAddress, username, keyFilename, command):
    async with asyncssh.connect(ipAddress, username=username, client_keys=[keyFilename]) as conn:
        result = await conn.run(command, check=True)
        return result.stdout, result.stderr

# 파일을 저장한다.
def write_json(new_data, file_name="data.json"):
    with open('devices.json', 'w+') as file:
        devices_file_data = json.loads(file) # 파일에서 정보를 불러온다.
        devices_file_data[new_data['id']] = new_data # 데이터를 추가하거나 갱신한다.
        json.dump(devices_file_data,file,indent=4) # 다시 파일로 저장한다.

        

@app.websocket("/ws/register")
async def websocketRegister(websocket : WebSocket):
    await websocket.accept()
    try:
        data = await websocket.receive_json() # 라즈베리 파이 데이터를 가지고 온다.
       
            
    except Exception as e:
        print(e)



# @app.websocket("/ws/command/{deviceName}")
# async def websocketCommand(websocket: WebSocket, deviceName: str):
#     await websocket.accept()
#     device = devices.get(deviceName)
#     if device:
#         try:
#             stdout, stderr = await runSshCommand(device['ip_address'], 'ssafy', r'C:\Users\LG\.ssh\id_rsa', 'python3 /home/ssafy/blink.py')
#             if stderr:
#                 await websocket.send_text(f"Error executing blink.py: {stderr}")
#             else:
#                 await websocket.send_text(f"Blinking LED on {device['description']} successfully started.")
#         except Exception as e:
#             await websocket.send_text(f"Failed to execute blink.py on {device['description']}. Error: {str(e)}")
#     else:
#         await websocket.send_text("Device not found")
#     await websocket.close()

# @app.websocket("/ws/status/{deviceName}")
# async def websocketStatus(websocket: WebSocket, deviceName: str):
#     await websocket.accept()
#     try:
#         while True:
#             status = await websocket.receive_text()
#             print(f"Received status from {deviceName}: {status}")
            
#     except Exception as e:
#         print(f"Error: {str(e)}")
#     finally:
#         await websocket.close()