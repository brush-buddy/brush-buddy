package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<Board> getBoardList(Map<String, String> param);
    boolean writeBoard();
    Board getDetail(Long boardId);

}
