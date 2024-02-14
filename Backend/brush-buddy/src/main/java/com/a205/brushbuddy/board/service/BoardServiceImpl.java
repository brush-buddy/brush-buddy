package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.*;
import com.a205.brushbuddy.board.dto.BoardDetailResponseDto;
import com.a205.brushbuddy.board.dto.BoardModifyRequestDto;
import com.a205.brushbuddy.board.dto.BoardWriteRequestDto;
import com.a205.brushbuddy.board.dto.ReplyWriteRequestDto;
import com.a205.brushbuddy.board.repository.*;
import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.draft.repository.Draft.DraftRepository;
import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.util.S3Uploader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
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
    public Page<Board> getBoardList(String search, Pageable pageable)  {
        Page<Board> result = null;
        try{
            //검색 수행이 아니라면
            if(search == null){
                result = boardRepository.findAllByBoardIsDeletedFalse(pageable);
            }
            else { // 검색을 수행하려면
                result = boardRepository.getSearchList(search, pageable);
            }
        }catch(Exception e){
            throw new BaseException(ErrorCode.BAD_REQUEST);
        }
        return result;
    }

    //게시글 작성
    @Override
    public boolean writeBoard(Integer userId, BoardWriteRequestDto requestDto) {
        try{
            //Board entity에 필요 데이터 우선 삽입
            Board entity = Board.builder()
                    .user(User.builder().userId(userId).build())
                    .boardTitle(requestDto.getTitle())
                    .boardContent(requestDto.getContents())
                    .boardThumbnail("")
                    .boardWatch(0)
                    .boardLikeNumber(0)
                    .boardIsDeleted(false)
                    .draft(requestDto.getDraftId() == null ? null : Draft.builder().draftId(requestDto.getDraftId()).build())
                    .build();

            //게시판 저장
            Board result = boardRepository.save(entity);

            //해시태그 테이블에 해당 게시글의 해시태그를 저장
            for(String hashtag : Set.copyOf(requestDto.getHashtags())){ // 각 해시태그에 대해
                hashtagRepository.save(Hashtag.builder() //저장하라
                        .id(HashtagPK.builder() // id는
                                .hashtagContent(hashtag) // hashtag와
                                .board(result) // 삽입 성공한 board를 복합키로
                                .build())
                        .build());
            }

            log.info(requestDto.getPhoto().toString());
            //게시판에 연관된 이미지 하나씩 저장
            for(BoardWriteRequestDto.PhotoDTO photoDTO : requestDto.getPhoto()){
                //base64 디코딩 및 s3 업로드
                String url = s3Uploader.uploadBase64Image(photoDTO.getImg(), "board"); //s3에 멀티파트 파일로 직접 업로드
                log.info(url);

                //이미지 엔티티 생성
                Image img = Image.builder()
                        .board(result) // 만든 게시판에 연결하기
                        .imageOrder(photoDTO.getOrder()) // 순서 유지
                        .imageUrl(url) // 이미지 s3 url
                        .build();

                //썸네일인지 확인하고 넣어주기
                if(photoDTO.getOrder() == 1){
                    result.setBoardThumbnail(url);
                    log.info("thumnail: " + url);
                    boardRepository.save(result);
                }

                //이미지 엔티티 저장
                imageRepository.save(img);
            }

        }catch(Exception e) {
            throw new BaseException(ErrorCode.BAD_REQUEST);
        }
        return true;
    }


    //게시글 상세 보기
    @Override
    public BoardDetailResponseDto getDetail(Integer userId, Long boardId) {
        // id로 보드 찾기
        Board result = boardRepository.findByBoardIdAndBoardIsDeletedFalse(boardId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_DATA));
        // 해당 게시글의 좋아요 여부 가지고 오기
        Optional<Heart> isHeart = heartRepository.findByHeartId_User_UserIdAndHeartId_Board_BoardId(userId, boardId);

        //해당 게시물의 조회수 증가
        result.setBoardWatch(result.getBoardWatch()+1);
        boardRepository.save(result);

        //해당 보드에 연결된 사진들 가지고 오기
        List<BoardDetailResponseDto.PhotoDTO> photos =
                imageRepository.findAllByBoard_BoardId(boardId)
                        .stream().map(
                        m -> BoardDetailResponseDto.PhotoDTO.builder() // 가져온 entity 기반 response dto 형식으로 변경
                                .order(m.getImageOrder())
                                .imgUrl(m.getImageUrl())
                                .build()
                ).toList();

        //게시판 관련 해시태그 추출
        List<String> hashtags = hashtagRepository.findAllById_Board_BoardId(boardId).stream().map(
                m -> m.getId().getHashtagContent()
        ).toList();

        //response DTO로 변환
        BoardDetailResponseDto.BoardDetailResponseDtoBuilder builder =
                BoardDetailResponseDto.builder()
                        .boardId(boardId)
                        .title(result.getBoardTitle())
                        .contents(result.getBoardContent())
                        .hashtag(hashtags)
                        .createdAt(result.getBoardTimestamp().toString())
                        .likeNumber(result.getBoardLikeNumber())
                        .thumbnail(result.getBoardThumbnail())
                        .views(result.getBoardWatch())
                        .isHeart(isHeart.isPresent()) // 좋아요 데이터 존재하면 체크하기
                        .isMine(result.getUser().getUserId().equals(userId)) // 가져온 게시판이 나의 게시판인가
                        .authorNickname(result.getUser().getUserNickname())
                        .photo(photos); // 가져온 사진 넣어주기

        //도안 있는지 확인하고 없으면 그냥 보내기
        if(result.getDraft() != null){
            builder.draftId(result.getDraft().getDraftId());//연결된 도안 있으면 넣어주기
        }

        //최종 결과 반환
        return builder.build();
    }

    //게시글 수정
    @Transactional
    @Override
    public boolean modifyBoard(Long boardId, Integer userId, BoardModifyRequestDto requestDto) {

        //보드 정보 가지고 오기
        Board board = boardRepository.findByBoardIdAndBoardIsDeletedFalse(boardId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_DATA));

        //User 확인을 통해서 수정권한 확인하기
        // TODO : Admin인지 확인하는 로직 필요
        if(Objects.equals(board.getUser().getUserId(), userId))
        {
            // 변경할 entity의 내용을 작성한다.
            board.setBoardTitle(requestDto.getTitle());
            board.setBoardContent(requestDto.getContents());

            //기존 이미지 삭제
            List<Image> images = imageRepository.findAllByBoard_BoardId(boardId);

            if(images != null){ // 기존 이미지가 있다면
                for(Image image : images){ // 모든 이미지에 대해서
                    //S3 내 파일 위치 추출
                    String filename = image.getImageUrl().split(".*//.*board/")[1]; // 이미지 url로 추출한다.
                    s3Uploader.removeS3File("board/"+filename); //이미지 파일 삭제
                }

                //DB에서 연관관계 삭제
                imageRepository.deleteAll(images);
            }

            //추가 이미지가 있다면
            if(!requestDto.getPhoto().isEmpty()){

                String thumbnail = null;

                for(BoardModifyRequestDto.PhotoDTO photo : requestDto.getPhoto()){ // 여러개면 조사
                    //새로운 이미지 업로드
                    String url = s3Uploader.uploadBase64Image(photo.getImg(), "board");

                    //이미지 DB에 저장하기
                    Image image = Image.builder()
                            .board(board)
                            .imageUrl(url)
                            .imageOrder(photo.getOrder())
                            .build();

                    imageRepository.save(image);

                    //Thumbnail 설정
                    if(photo.getOrder() == 1){ // 첫번째 순서라면
                        thumbnail = url; // 해당 이미지를 썸네일로 설정
                    }
                }

                // 썸네일 지정 안되면 오류 발생 시키기
                if(thumbnail == null) throw new BaseException(ErrorCode.BAD_REQUEST);

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
    @Transactional
    @Override
    public boolean deleteBoard(Integer userId, Long boardId)  {
        //게시판 정보 가져오기
        Board board = boardRepository.findByBoardIdAndBoardIsDeletedFalse(boardId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_DATA));

        // 만약 사용자가 작성한 게시글이면 삭제 처리
        // TODO : Admin인지 확인하는 로직 필요
        if(board.getUser().getUserId().equals(userId)){
            //board 삭제 여부 처리
            board.setBoardIsDeleted(true);

            //저장
            boardRepository.save(board);
            return true;
        }else{
            return false;
        }
    }

    //댓글 목록 조회하기
    @Override
    public Page<Reply> getReplies(Long boardId, Pageable pageable){
        try{
            return replyRepository.findAllByBoard_BoardIdAndBoard_BoardIsDeletedFalseAndReplyIsDeletedFalse(boardId, pageable);
        }catch(Exception e){
            throw new BaseException(ErrorCode.NOT_FOUND_DATA);
        }

    }

    //댓글 작성하기
    @Transactional
    @Override
    public boolean writeReply(Integer userId, Long boardId, ReplyWriteRequestDto requestDto){
        // board 찾기
        Board board = boardRepository.findByBoardIdAndBoardIsDeletedFalse(boardId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_DATA));

        //댓글 생성
        Reply reply = Reply.builder()
                .board(board)
                .user(User.builder().userId(userId).build())
                .replyIsDeleted(false)
                .replyContent(requestDto.getContents())
                .build();

        // 댓글 db에 저장
        replyRepository.save(reply);
        return false;
    }

    //댓글 삭제하기
    @Transactional
    @Override
    public boolean deleteReply(Integer userId, Long replyId) {
        Reply reply = replyRepository.findByIdAndReplyIsDeletedFalse(replyId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_DATA));

        //권한이 있는지 파악하기
        // TODO : Admin인지 확인하는 로직 필요
        if(reply.getUser().getUserId().equals(userId)) {
            // 삭제처리
            reply.setReplyIsDeleted(true);
            //변경사항 저장
            replyRepository.save(reply);
        }else {
            throw new BaseException(ErrorCode.NOT_PRIVIEGED);
        }

        return true;
    }


    //좋아요 누르기
    @Transactional
    @Override
    public boolean addHeart(Integer userId, Long boardId){
        //게시물 탐색
        Board board = boardRepository.findByBoardIdAndBoardIsDeletedFalse(boardId).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_DATA));

        //좋아요 처리
        heartRepository.insertHeart(userId, boardId);

        //해당 게시물의 좋아요 수 증가
        board.setBoardLikeNumber(board.getBoardLikeNumber() + 1); //
        return true;
    }

    //좋아요 취소
    @Transactional
    @Override
    public boolean deleteHeart(Integer userId, Long boardId) {
        //엔티티 존재 확인 로직
        Board board = boardRepository.findByBoardIdAndBoardIsDeletedFalse(boardId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_DATA));
        Heart heart =  heartRepository.findByHeartId_User_UserIdAndHeartId_Board_BoardId(userId, boardId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_DATA));

        // 엔티티에서 삭제하기
        heartRepository.delete(heart);

        //해당 게시물의 좋아요 수 감소
        board.setBoardLikeNumber(board.getBoardLikeNumber() - 1);

        return true;
    }
}
