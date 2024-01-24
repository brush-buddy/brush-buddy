package com.a205.brushbuddy.board.controller;


import com.a205.brushbuddy.board.dto.BoardListRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/board")
public class BoardController {

    @GetMapping("/list")
    public ResponseEntity<?> getBoardList(BoardListRequestDto requestDto){
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    public ResponseEntity<?> getBoardList(){
        return ResponseEntity.ok().build();
    }


}
