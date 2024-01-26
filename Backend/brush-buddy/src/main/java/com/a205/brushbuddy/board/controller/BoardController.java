package com.a205.brushbuddy.board.controller;


import com.a205.brushbuddy.board.dto.BoardListRequestDto;
import com.a205.brushbuddy.board.dto.BoardWriteRequestDto;
import com.a205.brushbuddy.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<?> getBoardList(BoardListRequestDto requestDto){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/write")
    public ResponseEntity<?> writeBoard(@RequestBody BoardWriteRequestDto requestDto){
        boardService.writeBoard(requestDto);
        return ResponseEntity.ok().build();
    }


}
