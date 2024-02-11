import socket
import threading

# 서버 설정
HOST = '0.0.0.0'
PORT = 3004

# 연결된 클라이언트 관리를 위한 리스트
clients = {}

# 클라이언트와 연결 관리하는 함수
def handle_client(client_socket, addr):
    print(f"클라이언트 {addr}가 연결되었습니다.")

    # 클라이언트 식별자 설정
    client_id = f"{addr[0]}:{addr[1]}"
    print(client_id)
    clients[client_id] = client_socket

    while True:
        try:
            data = client_socket.recv(1024)
        except ConnectionResetError as e:
            print(f"클라이언트 {addr} 가 강제 종료하였습니다.")
            break

        if not data:
            break
        print(f"클라이언트 {addr}로부터 받은 메시지: {data.decode()}")

        # 메시지를 특정 클라이언트에게 전달
        if data.startswith(b"@"):
            recipient_id, message = data.decode().split(" ", 1)
            recipient_socket = clients.get(recipient_id[1:])
            if recipient_socket:
                recipient_socket.sendall(message.encode())
        else:
            # 모든 클라이언트에게 메시지 전송
            for client_id, socket in clients.items():
                if socket != client_socket:
                    socket.sendall(data)

    print(f"클라이언트 {addr}와의 연결을 종료합니다.")
    del clients[client_id]
    client_socket.close()



# 서버 시작
def start_server():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
        server_socket.bind((HOST, PORT))
        server_socket.listen()

        print(f"서버가 {HOST}:{PORT}에서 시작되었습니다.")

        while True:
            try :
                print("소켓 accept 대기 중...")
                client_socket, addr = server_socket.accept()

                print("소켓 accept 완료")
                print("소켓 스레드 실행 중...")

                client_handler = threading.Thread(target=handle_client, args=(client_socket, addr))
                client_handler.start()
                print(f"클라이언트 {addr} 에 대하여 소켓 스레드를 실행 하였습니다.")

            except KeyboardInterrupt :
                print("서버를 종료합니다.")
                server_socket.close()
     

if __name__ == "__main__":
    start_server()
