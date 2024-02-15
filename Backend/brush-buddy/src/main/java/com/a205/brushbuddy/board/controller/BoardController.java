package com.a205.brushbuddy.board.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.Reply;
import com.a205.brushbuddy.board.dto.BoardDetailResponseDto;
import com.a205.brushbuddy.board.dto.BoardListRequestDto;
import com.a205.brushbuddy.board.dto.BoardListResponseDto;
import com.a205.brushbuddy.board.dto.BoardModifyRequestDto;
import com.a205.brushbuddy.board.dto.BoardWriteRequestDto;
import com.a205.brushbuddy.board.dto.ReplyListRequestDto;
import com.a205.brushbuddy.board.dto.ReplyListResponseDto;
import com.a205.brushbuddy.board.dto.ReplyWriteRequestDto;
import com.a205.brushbuddy.board.service.BoardService;
import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final JwtUtil jwtUtil;

    // 게시글 검색 및 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<?> getBoardList(BoardListRequestDto requestDto) {

        Pageable pageable = PageRequest.of(requestDto.getPageNum() - 1, //현재 페이지
            requestDto.getListNum(), // 페이지 당 개수
            requestDto.getDirection(), //오름차순 or 내림차순
            requestDto.getOrder()); // 기준

        Page<Board> boards = boardService.getBoardList(requestDto.getSearch(), pageable); //정렬 기준

        //결과물 dto로 변환
        BoardListResponseDto result = BoardListResponseDto.builder()
            .boards(boards.getContent().stream().map(
                m -> BoardListResponseDto.BoardDTO.builder()
                    .boardId(m.getBoardId())
                    .boardTitle(m.getBoardTitle())
                    .views(m.getBoardWatch())
                    .thumbnail(m.getBoardThumbnail())
                    .likeNumber(m.getBoardLikeNumber())
                    .createdAt(m.getBoardTimestamp().toString())
                    .build()).toList())
            .length(boards.getNumberOfElements())
            .pageNum(boards.getNumber() + 1)
            .totalPage(boards.getTotalPages())
            .build();

        return ResponseEntity.ok(result);
    }

    // 게시판 작성
    @PostMapping("")
    public ResponseEntity<?> writeBoard(@RequestBody BoardWriteRequestDto requestDto, HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request)
            .orElseThrow(() -> new BaseException(
                ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        boardService.writeBoard(userId, requestDto);
        return ResponseEntity.ok().build();
    }

    // 게시판 상세조회
    @GetMapping("/{boardId}")
    public  ResponseEntity<?> detailBoard(@PathVariable long boardId, HttpServletRequest request){
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        BoardDetailResponseDto result = boardService.getDetail(userId, boardId);
        return ResponseEntity.ok().body(result);
    }

    //게시판 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<?> modifyBoard(@PathVariable long boardId, @RequestBody BoardModifyRequestDto requestDto,
        HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request)
            .orElseThrow(() -> new BaseException(
                ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        boardService.modifyBoard(boardId, userId, requestDto);
        return ResponseEntity.ok().build();
    }

    //게시판 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable long boardId, HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request)
            .orElseThrow(() -> new BaseException(
                ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        log.info(String.valueOf(userId));
        boardService.deleteBoard(userId, boardId);
        return ResponseEntity.ok().build();
    }

    //댓글 리스트 조회
    @GetMapping("/{boardId}/replies")
    public ResponseEntity<?> getReplies(@PathVariable long boardId, ReplyListRequestDto requestDto, HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송

        // pageable 생성
        Pageable pageable = PageRequest.of(requestDto.getPageNum() - 1, requestDto.getListNum(), Sort.Direction.DESC,
            "replyTimestamp");

        // 페이지 네이션 댓글 가지고 오기
        Page<Reply> replies = boardService.getReplies(boardId, pageable);

        ReplyListResponseDto result = ReplyListResponseDto.builder()

            .replyList(replies.getContent().stream().map(
                    m -> ReplyListResponseDto.replyDTO
                        .builder()
                        .replyId(m.getId())
                        .userId(m.getUser().getUserId())
                        .nickname(m.getUser().getUserNickname())
                        .contents(m.getReplyContent())
                        .createdAt(m.getReplyTimestamp().toString())
                        .isMine(m.getUser().getUserId().equals(userId))
                        .build())
                .toList())
            .pageNum(replies.getNumber() + 1)
            .length(replies.getNumberOfElements())
            .totalPage(replies.getTotalPages())
            .build();
        return ResponseEntity.ok(result);
    }

    //댓글 작성
    @PostMapping("/{boardId}/replies")
    public ResponseEntity<?> writeReply(@PathVariable long boardId,
        @RequestBody ReplyWriteRequestDto replyWriteRequestDto, HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request)
            .orElseThrow(() -> new BaseException(
                ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        boardService.writeReply(userId, boardId, replyWriteRequestDto);
        return ResponseEntity.ok().build();
    }

    //댓글 삭제
    @DeleteMapping("/{boardId}/replies/{replyId}")
    public ResponseEntity<?> deleteReply(@PathVariable long boardId, @PathVariable long replyId,
        HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request)
            .orElseThrow(() -> new BaseException(
                ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        boardService.deleteReply(userId, replyId);
        return ResponseEntity.ok().build();
    }

    //좋아요 추가
    @PostMapping("/{boardId}/heart")
    public ResponseEntity<?> addHeart(@PathVariable long boardId, HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request)
            .orElseThrow(() -> new BaseException(
                ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        boardService.addHeart(userId, boardId);
        return ResponseEntity.ok().build();
    }

    //좋아요 삭제
    @DeleteMapping("/{boardId}/heart")
    public ResponseEntity<?> deleteHeart(@PathVariable long boardId, HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request)
            .orElseThrow(() -> new BaseException(
                ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        boardService.deleteHeart(userId, boardId);
        return ResponseEntity.ok().build();
    }

}
