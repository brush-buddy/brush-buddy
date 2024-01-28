package com.a205.brushbuddy.board.controller;


import com.a205.brushbuddy.board.dto.BoardDetailResponseDto;
import com.a205.brushbuddy.board.dto.BoardListRequestDto;
import com.a205.brushbuddy.board.dto.BoardModifyRequestDto;
import com.a205.brushbuddy.board.dto.BoardWriteRequestDto;
import com.a205.brushbuddy.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 검색 및 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<?> getBoardList(BoardListRequestDto requestDto) throws Exception{
        return ResponseEntity.ok().build();
    }


    // 게시판 작성
    @PostMapping("")
    public ResponseEntity<?> writeBoard(@RequestBody BoardWriteRequestDto requestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출하기
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
        //TODO : userId JWT 토큰으로 부터 추출하기
        Integer userId = 1;
        boardService.modifyBoard(boardId, userId, requestDto);
        return ResponseEntity.ok().build();
    }

    //게시판 삭제
    @DeleteMapping("/{boardId}")
    public  ResponseEntity<?> deleteBoard(@PathVariable long boardId) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출하기
        Integer userId = 1;
        boardService.deleteBoard(userId, boardId);
        return ResponseEntity.ok().build();
    }


    //댓글 리스트 조회
    @GetMapping("/{boardId}/comments")
    public  ResponseEntity<?> getComments(@PathVariable long boardId) throws Exception{
        return ResponseEntity.ok().build();
    }

    //댓글 작성
    @PostMapping("/{boardId}/comments")
    public  ResponseEntity<?> writComment(@PathVariable long boardId) throws Exception{
        return ResponseEntity.ok().build();
    }

    //댓글 삭제
    @DeleteMapping("/{boardId}/comments/{commentsId}")
    public  ResponseEntity<?> deleteComments(@PathVariable long boardId, @PathVariable long commentsId) throws Exception{
        return ResponseEntity.ok().build();
    }

    //좋아요 추가
    @PostMapping("/{boardId}/heart")
    public  ResponseEntity<?> addHeart(@PathVariable long boardId) throws Exception{
        return ResponseEntity.ok().build();
    }

    //좋아요 삭제
    @DeleteMapping ("/{boardId}/heart")
    public  ResponseEntity<?> deleteHeart(@PathVariable long boardId) throws Exception{
        return ResponseEntity.ok().build();
    }

}
