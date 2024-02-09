import serial
import asyncio
import websockets

ser = serial.Serial('/dev/ttyACM0', 9600)

async def send_blink_notification():
    uri = "ws://192.168.148.60:8000/ws/blink/raspberry_pi_1"
    async with websockets.connect(uri) as websocket:
        while True:
            ser.write(b'H')
            await websocket.send("The LED blinked")
            await asyncio.sleep(1)

            ser.write(b'L')
            await asyncio.sleep(1)

async def main():
    await asyncio.sleep(2)
    await send_blink_notification()

asyncio.run(main())