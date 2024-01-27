package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.Hashtag;
import com.a205.brushbuddy.board.domain.HashtagPK;
import com.a205.brushbuddy.board.domain.Image;
import com.a205.brushbuddy.board.dto.*;
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

    //게시글 조회 및 검색
    @Override
    public List<Board> getBoardList(Map<String, String> param) {
        return null;
    }

    //게시글 작성
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

            //해시태그 테이블에 해당 게시글의 해시태그를 저장
            for(String hashtag : requestDto.getHashtags()){ // 각 해시태그에 대해
                hashtagRepository.save(Hashtag.builder() //저장하라
                        .id(HashtagPK.builder() // id는
                                .hashtagContent(hashtag) // hashtag와
                                .board(result) // 삽입 성공한 board를 복합키로
                                .build())
                        .build());
            }

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


    //게시글 상세 보기
    @Override
    public BoardDetailResponseDto getDetail(Long boardId) {
        return null;
    }

    //게시글 수정
    @Override
    public boolean modifyBoard(BoardModifyRequestDto requestDto) {
        return false;
    }

    //게시글 삭제
    @Override
    public boolean deleteBoard(String userId, Long boardId) {
        return false;
    }

    //코멘트 달기
    @Override
    public List<CommentListResponseDto> getComments(Long BoardId, CommentWriteRequestDto requestDto) {
        return null;
    }

    @Override
    public boolean writeComment(CommentWriteRequestDto requestDto) {
        return false;
    }

    @Override
    public boolean deleteComment(Long boardId, Long commentId) {
        return false;
    }

    @Override
    public boolean addHeart(String userId, Long commentId) {
        return false;
    }
}
