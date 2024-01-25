package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.dto.BoardWriteRequestDto;
import com.a205.brushbuddy.board.repository.BoardRepository;
import com.a205.brushbuddy.util.S3Uploader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final S3Uploader s3Uploader;

    @Override
    public List<Board> getBoardList(Map<String, String> param) {
        return null;
    }

    @Override
    @Transactional
    public boolean writeBoard(BoardWriteRequestDto requestDto) {

        try{

            for(BoardWriteRequestDto.PhotoDTO photoDTO : requestDto.getPhoto()){
                byte[] image = Base64.getDecoder().decode(photoDTO.getImg());

                int totalCnt = 1024;
                try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(totalCnt)) {
                    int offset = 0;
                    while (offset < image.length) {
                        int chunkSize = Math.min(totalCnt, image.length - offset);

                        byte[] byteArray = new byte[chunkSize];
                        System.arraycopy(image, offset, byteArray, 0, chunkSize);

                        byteArrayOutputStream.write(byteArray);
                        byteArrayOutputStream.flush();

                        offset += chunkSize;
                    }

                    // ByteArrayOutputStream -> ByteArrayInputStream
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

                    // MultipartFile 객체 생성
                    MultipartFile multipartFile = new MockMultipartFile("파일명", byteArrayInputStream.readAllBytes());

                    s3Uploader.uploadFiles(multipartFile, "/board"); //s3에 멀티파트 파일로 직접 업로드
                } catch (IOException e) {
                    return false;
                }

            }
            Board entity = Board.builder()
                    .boardTitle(requestDto.getTitle())
                    .boardContent(requestDto.getContents())
                    .boardWatch(0)
                    .
                    .build();

            boardRepository.save(entity);
        }catch(Exception e) {
            return false;
        }

        return true;

    }

    @Override
    public Board getDetail(Long boardId) {
        return null;
    }
}
