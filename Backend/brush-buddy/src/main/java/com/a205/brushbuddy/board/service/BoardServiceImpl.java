package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.Image;
import com.a205.brushbuddy.board.dto.BoardWriteRequestDto;
import com.a205.brushbuddy.board.repository.*;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                //base64 디코딩 및 s3 업로드
                String url = s3Uploader.uploadBase64Image(photoDTO.getImg(), "board"); //s3에 멀티파트 파일로 직접 업로드

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
