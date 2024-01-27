package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.Hashtag;
import com.a205.brushbuddy.board.domain.HashtagPK;
import com.a205.brushbuddy.board.domain.Image;
import com.a205.brushbuddy.board.dto.*;
import com.a205.brushbuddy.board.repository.*;
import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.draft.repository.DraftRepository;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final HashtagRepository hashtagRepository;
    private final HeartRepository heartRepository;
    private final ImageRepository imageRepository;
    private final ReplyRepository replyRepository;
    private final DraftRepository draftRepository;
    private final S3Uploader s3Uploader;

    //게시글 조회 및 검색
    @Override
    public List<Board> getBoardList(Map<String, String> param) throws Exception {
        return null;
    }

    //게시글 작성
    @Override
    public boolean writeBoard(BoardWriteRequestDto requestDto) throws Exception{
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
    public BoardDetailResponseDto getDetail(Long boardId) throws Exception{
        // id로 보드 찾기
        Board result = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception("couldn't get Board detail by boardId"));

        //해당 보드에 연결된 사진들 가지고 오기
        List<BoardDetailResponseDto.PhotoDTO> photos =
                imageRepository.findAllByBoard_BoardId(boardId).stream().map(
                        m -> BoardDetailResponseDto.PhotoDTO.builder() // 가져온 entity 기반 response dto 형식으로 변경
                                .order(m.getImageOrder())
                                .imgUrl(m.getImageUrl())
                                .build()
                ).toList();

        //response DTO로 변환 및 반환
        return BoardDetailResponseDto.builder()
                .boardId(boardId)
                .title(result.getBoardTitle())
                .contents(result.getBoardContent())
                .createdAt(result.getBoardTimestamp().toString())
                .likeNumber(result.getBoardLikeNumber())
                .thumbnail(result.getBoardThumbnail())
                .views(result.getBoardWatch())
                .draftId(result.getDraft().getDraftId()) //연결된 거 있으면 넣어주기
                .photo(photos) // 가져온 사진 넣어주기
                .build();
    }
    //게시글 수정
    @Override
    public boolean modifyBoard(Long boardId, Integer userId, BoardModifyRequestDto requestDto) throws Exception{

        //보드 정보 가지고 오기
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception("invalid boardId"));
        //User 확인을 통해서 수정권한 확인하기
        if(board.getUser().getUserId() == userId)
        {
            // 변경할 entity의 내용을 작성한다.
            board.setBoardTitle(requestDto.getTitle());
            board.setBoardContent(requestDto.getContents());

            //Thumbnail 설정
            if(!requestDto.getPhoto().isEmpty()){
                String thumbnail = requestDto.getPhoto().get(0).getImg(); // 첫번째꺼로 기본 설정

                for(BoardModifyRequestDto.PhotoDTO photo : requestDto.getPhoto()){ // 여러개면 조사
                    if(photo.getOrder() == 1){ // 첫번째 순서라면
                        thumbnail = photo.getImg(); // 해당 이미지를 썸네일로 설정
                        break;
                    }
                }
                board.setBoardThumbnail(thumbnail); // 썸네일 변경
            }

            //도안 변경
            Draft draft =  draftRepository.findById(requestDto.getDraftId()).orElse(null);
            board.setDraft(draft);

            //변경 내용을 작성한다.
            boardRepository.save(board);
            return true;

        }else{
            return false;
        }


    }

    //게시글 삭제
    @Override
    public boolean deleteBoard(Integer userId, Long boardId) throws Exception {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception("couldn't find board by boardId"));
        // 만약 사용자가 작성한 게시글이면 삭제 처리
        if(board.getUser().getUserId().equals(userId)){
            boardRepository.delete(board);
            return true;
        }else{
            return false;
        }
    }

    //코멘트 달기
    @Override
    public List<CommentListResponseDto> getComments(Long BoardId, CommentWriteRequestDto requestDto) throws Exception{
        return null;
    }

    @Override
    public boolean writeComment(CommentWriteRequestDto requestDto) throws Exception{
        return false;
    }

    @Override
    public boolean deleteComment(Long boardId, Long commentId) throws Exception{
        return false;
    }

    @Override
    public boolean addHeart(Integer userId, Long commentId) throws Exception{
        return false;
    }
}
