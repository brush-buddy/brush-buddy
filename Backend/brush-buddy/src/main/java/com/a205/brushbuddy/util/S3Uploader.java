package com.a205.brushbuddy.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFiles(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));
        return upload(uploadFile, dirName);
    }

    public String upload(File uploadFile, String filePath) {
        String fileName = filePath + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    public String uploadBase64Image(String image, String dirPath){

        //meta 정보와 이미지 정보 분리
        String delims="[,]";
        String[] parts = image.split(delims);

        //이미지 정보 디코딩 및 바이트스트림으로 변경
        byte[] imageBytes = Base64.getDecoder().decode(parts[1]);

        int totalCnt = 1024;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(totalCnt)) {
            int offset = 0;
            while (offset < imageBytes.length) {
                int chunkSize = Math.min(totalCnt, imageBytes.length - offset);

                byte[] byteArray = new byte[chunkSize];
                System.arraycopy(imageBytes, offset, byteArray, 0, chunkSize);

                byteArrayOutputStream.write(byteArray);
                byteArrayOutputStream.flush();

                offset += chunkSize;
            }

            // ByteArrayOutputStream -> ByteArrayInputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            //이미지 타입 추출
            String mimeType = null;
            String fileExtension = null;

            try {
                mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream); //mimeType is something like "image/jpeg"
                String delimiter = "[/]";
                String[] tokens = mimeType.split(delimiter);
                fileExtension = tokens[1];
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            String fileName  = "image."+fileExtension;
            // MultipartFile 객체 생성
            // MultipartFile multipartFile = new MockMultipartFile(fileName, byteArrayInputStream.readAllBytes());

            //파일 생성
            File convertFile = new File(System.getProperty("user.dir") + "/" + fileName);
            if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
                try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                    fos.write(imageBytes);
                }
            }

            //S3에 이미지 저장하고 url 가져오기
            return upload(convertFile, "board"); //s3에 멀티파트 파일로 직접 업로드
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            System.out.println("File delete success");
            return;
        }
        System.out.println("File delete fail");
    }

    // 로컬에 파일 업로드 하기
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("user.dir") + "/" + file.getName());
        if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    public void removeS3File(String filepath){
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket ,filepath));
    }
}

