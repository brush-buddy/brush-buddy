package com.a205.brushbuddy.board.controller;


import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.dto.*;
import com.a205.brushbuddy.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 검색 및 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<?> getBoardList(BoardListRequestDto requestDto) throws Exception{
        List<Board> boards = boardService.getBoardList(requestDto.getSearch(),
                PageRequest.of(requestDto.getPageNum(), //현재 페이지
                        requestDto.getListNum(), // 페이지 당 개수
                        requestDto.getDirection(), //오름차순 or 내림차순
                        requestDto.getOrder())); //정렬 기준

        return ResponseEntity.ok().body(boards);
    }


    // 게시판 작성
    @PostMapping("")
    public ResponseEntity<?> writeBoard(@RequestBody BoardWriteRequestDto requestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        boardService.writeBoard(userId, requestDto);
        return ResponseEntity.ok().build();
    }

    // 게시판 상세조회
    @GetMapping("/{boardId}")
    public  ResponseEntity<?> detailBoard(@PathVariable long boardId) throws Exception{

        BoardDetailResponseDto result = boardService.getDetail(boardId);
        return ResponseEntity.ok().body(result);
    }


    //게시판 수정
    @PutMapping("/{boardId}")
    public  ResponseEntity<?> modifyBoard(@PathVariable long boardId, @RequestBody BoardModifyRequestDto requestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        boardService.modifyBoard(boardId, userId, requestDto);
        return ResponseEntity.ok().build();
    }

    //게시판 삭제
    @DeleteMapping("/{boardId}")
    public  ResponseEntity<?> deleteBoard(@PathVariable long boardId) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        boardService.deleteBoard(userId, boardId);
        return ResponseEntity.ok().build();
    }


    //댓글 리스트 조회
    @GetMapping("/{boardId}/replies")
    public  ResponseEntity<?> getReplies(@PathVariable long boardId) throws Exception{
        return ResponseEntity.ok().build();
    }

    //댓글 작성
    @PostMapping("/{boardId}/replies")
    public  ResponseEntity<?> writeReply(@PathVariable long boardId, @RequestBody ReplyWriteRequestDto replyWriteRequestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        boardService.writeReply(userId, boardId, replyWriteRequestDto);
        return ResponseEntity.ok().build();
    }

    //댓글 삭제
    @DeleteMapping("/{boardId}/replies/{replyId}")
    public  ResponseEntity<?> deleteReply(@PathVariable long boardId, @PathVariable long replyId) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        boardService.deleteReply(userId, replyId);
        return ResponseEntity.ok().build();
    }

    //좋아요 추가
    @PostMapping("/{boardId}/heart")
    public  ResponseEntity<?> addHeart(@PathVariable long boardId) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        boardService.addHeart(userId, boardId);
        return ResponseEntity.ok().build();
    }

    //좋아요 삭제
    @DeleteMapping ("/{boardId}/heart")
    public  ResponseEntity<?> deleteHeart(@PathVariable long boardId) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        boardService.deleteHeart(userId, boardId);
        return ResponseEntity.ok().build();
    }

}
