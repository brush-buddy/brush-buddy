import io
import os

import boto3
from pydantic import BaseModel


class AwsS3(BaseModel):
    # S3 연결 함수
    def s3_connection(self):

        # 환경변수 불러오기
        AWS_ACCESS_KEY_ID = os.getenv("S3_ACCESS_KEY")
        AWS_SECRET_ACCESS_KEY = os.getenv("S3_SECRET_KEY")

        try:
            s3 = boto3.client(
                service_name="s3",
                region_name="ap-northeast-2",  # 자신이 설정한 bucket region
                aws_access_key_id=AWS_ACCESS_KEY_ID,
                aws_secret_access_key=AWS_SECRET_ACCESS_KEY,
            )

        except Exception as e:
            print(e)
        else:
            # print("s3 bucket connected!")
            return s3

    def upload_Pillow_image_to_s3(
        self, bucket_name, file_key, pillow_image, content_type
    ):
        """
        NumPy 배열 형식의 이미지를 S3 버킷에 업로드합니다.
        :param bucket_name: S3 버킷 이름
        :param file_key: 업로드할 파일의 키 (파일 경로와 파일 이름을 포함)
        :param numpy_array: NumPy 배열 형식의 이미지 데이터
        :param content_type: 업로드할 파일의 컨텐츠 타입 (예: 'image/jpeg', 'image/png' 등)
        """

        # Pillow 이미지를 메모리에서 바이너리 데이터로 저장
        with io.BytesIO() as buffer:
            pillow_image.save(buffer, format="png")
            image_binary = buffer.getvalue()

        # S3 클라이언트 생성 및 파일 업로드
        s3_client = boto3.client("s3")
        s3_client.put_object(
            Bucket=bucket_name,
            Key=file_key,
            Body=image_binary,
            ContentType=content_type,
        )

        return f"https://{bucket_name}.s3.ap-northeast-2.amazonaws.com/{file_key}"
