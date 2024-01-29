package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Board;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardSearchCustom {
    List<Board> getSearchList(String search, Pageable pageable);
}
