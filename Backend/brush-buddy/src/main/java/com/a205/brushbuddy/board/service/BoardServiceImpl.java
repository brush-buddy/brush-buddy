package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.Image;
import com.a205.brushbuddy.board.dto.BoardWriteRequestDto;
import com.a205.brushbuddy.board.repository.*;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final HashtagRepository hashtagRepository;
    private final HeartRepository heartRepository;
    private final ImageRepository imageRepository;
    private final ReplyRepository replyRepository;
    private final S3Uploader s3Uploader;

    @Override
    public List<Board> getBoardList(Map<String, String> param) {
        return null;
    }

    @Override
    public boolean writeBoard(BoardWriteRequestDto requestDto) {
        try{
            //Board entity에 필요 데이터 우선 삽입
            Board entity = Board.builder()
                    .user(User.builder().userId(1).build()) // TEST CODE
                    //TODO : JWT 토크에서 사용자 로그 가져오기
                    .boardTitle(requestDto.getTitle())
                    .boardContent(requestDto.getContents())
                    .boardThumbnail("")
                    .boardWatch(0)
                    .boardLikeNumber(0)
                    .build();

            //게시판 저장
            Board result = boardRepository.save(entity);

            //게시판에 연관된 이미지 하나씩 저장
            for(BoardWriteRequestDto.PhotoDTO photoDTO : requestDto.getPhoto()){
                //base64 디코딩 및 multipartfile로 변환

                //meta 정보와 이미지 정보 분리
                String delims="[,]";
                String[] parts = photoDTO.getImg().split(delims);

                //이미지 정보 디코딩 및 바이트스트림으로 변경
                byte[] image = Base64.getDecoder().decode(parts[1]);

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

                    //이미지 타입 추출
                    String mimeType = null;
                    String fileExtension = null;

                    try {
                        mimeType = URLConnection.guessContentTypeFromStream(byteArrayInputStream); //mimeType is something like "image/jpeg"
                        String delimiter="[/]";
                        String[] tokens = mimeType.split(delimiter);
                        fileExtension = tokens[1];
                    } catch (IOException ioException){
                        ioException.printStackTrace();
                    }

                    // MultipartFile 객체 생성
                    MultipartFile multipartFile = new MockMultipartFile("image"+"."+fileExtension, byteArrayInputStream.readAllBytes());

                    //S3에 이미지 저장하고 url 가져오기
                    String url = s3Uploader.uploadFiles(multipartFile, "board"); //s3에 멀티파트 파일로 직접 업로드

                    //이미지 엔티티 생성
                    Image img = Image.builder()
                            .board(result) // 만든 게시판에 연결하기
                            .imageOrder(photoDTO.getOrder()) // 순서 유지
                            .imageUrl(url) // 이미지 s3 url
                            .build();

                    //썸네일인지 확인하고 넣어주기
                    if(photoDTO.getOrder() == 1){
                        result.setBoardThumbnail(url);
                        boardRepository.save(result);
                    }

                    //이미지 엔티티 저장
                    imageRepository.save(img);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Board getDetail(Long boardId) {
        return null;
    }
}
