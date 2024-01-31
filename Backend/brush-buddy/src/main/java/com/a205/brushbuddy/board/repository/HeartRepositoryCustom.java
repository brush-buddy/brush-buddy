package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HeartRepositoryCustom {
    public boolean insertHeart(Integer userId, Long boardId);

    Page<Board> getHeartBoardList(Integer userId,String search, Pageable pageable); // 하트한 리스트 반환
}
