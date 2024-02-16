import asyncio
import websockets
import time
import threading
import json
import serial


client_id = 1
uri = f"ws://i10a205.p.ssafy.io:3004/ws/{client_id}"
connected_websocket = ''
timeout = 10
ping_pong_time = 10
ser1 = serial.Serial('/dev/ttyACM0', 9600, timeout=1)
ser2 = serial.Serial('/dev/ttyACM1', 9600, timeout=1)
time.sleep(2)


async def main():
    async with websockets.connect(uri) as websocket:
        try:
            while True:
                response = await websocket.recv()
                print(f"< Response from server: {response}")  # Pong

                if response.startswith("CMD"):
                    data = json.loads(response.split(" ", 1)[1])
                    print("Received a command processing request.")
                    result = await process(data)  # Process the data
                    print("Command has been processed.")

                    if result:
                        print("Sending success message to server...")
                        await websocket.send(f"RESULT{client_id}: Success => {result}")
                    else:
                        print("Sending failure message to server...")
                        await websocket.send(f"RESULT{client_id}: Fail => {result}")
                else:
                    await websocket.send(response)  # Send back what was received

        except KeyboardInterrupt as e:
            print("Closing client")
        finally:
            websocket.close()

        # If a command and data are received in the meantime, extract the data then execute the command.



async def ping(websocket, ping_pong_interval_sec=10, message="keep going"):
    await websocket.send(message)
    response = await websocket.recv()
    print(f"< Response from the server: {response}")
    await asyncio.sleep(ping_pong_interval_sec)


async def process(data):
    color_data = data.get("color", {})
    scale = 100
    cc = color_data.get('c', 0) * scale
    mm = color_data.get('m', 0) * scale
    yy = color_data.get('y', 0) * scale
    kk = color_data.get('k', 0) * scale
    ww = color_data.get('w', 0) * scale

    command1 = f"{ww}, {kk}, 0, 0\n"
    command2 = f"0, {cc}, {mm}, {yy}\n"
    ser1.write(command1.encode())
    ser2.write(command2.encode())
    print(command1)
    print(command2)

    completed_messages = 0
    max_wait_time = 30

    end_time = asyncio.get_event_loop().time() + max_wait_time
    while asyncio.get_event_loop().time() < end_time and completed_messages < 2:
        if ser1.in_waiting > 0:
            line1 = ser1.readline().decode().strip()
            # if len(line1):
            #    print(line1)
            if line1 == "Motor operation completed":
                completed_messages += 1
                print("Arduino 1: Motor operation completed")
        if ser2.in_waiting > 0:
            line2 = ser2.readline().decode().strip()
            if line2 == "Motor operation completed":
                completed_messages += 1
                print("Arduino 2: Motor operation completed")
        await asyncio.sleep(0.1)

    if completed_messages == 2:
        print("All motors completed operation successfully.")
        return True
    else:
        print("Failed to complete motor operations within the expected time.")
        return False

asyncio.run(main())
