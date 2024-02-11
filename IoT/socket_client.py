import socket
import threading
import time

# 서버 설정
SERVER_HOST = '127.0.0.1'
SERVER_PORT = 3004

# 서버에 메시지를 보내는 함수
def send_message(sock):
    try:
        while True:
            message = input("메시지를 입력하세요: ")
            sock.sendall(message.encode())
    except ConnectionResetError as e:
            print("연결이 종료되어 프로그램을 종료합니다.")
# 서버로부터 메시지를 받는 함수
def receive_message(sock):
    try:
        while True:
            message = sock.recv(1024).decode()
            print(f"서버로부터 받은 메시지: {message}")
    except ConnectionResetError as e:
        print("연결이 종료되어 프로그램을 종료합니다.")

if __name__ == "__main__":
    
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
            client_socket.connect((SERVER_HOST, SERVER_PORT))

            send_thread = threading.Thread(target=send_message, args=(client_socket,))
            receive_thread = threading.Thread(target=receive_message, args=(client_socket,))

            send_thread.start()
            receive_thread.start()

            send_thread.join()
            receive_thread.join()
    
        

