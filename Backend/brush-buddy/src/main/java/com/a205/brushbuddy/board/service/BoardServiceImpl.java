package com.a205.brushbuddy.board.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public List<Board> getBoardList(Map<String, String> param) {
        return null;
    }

    @Override
    public boolean writeBoard() {
        return false;
    }

    @Override
    public Board getDetail(Long boardId) {
        return null;
    }
}
