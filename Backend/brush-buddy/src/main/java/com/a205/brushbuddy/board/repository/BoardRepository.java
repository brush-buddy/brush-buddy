package com.a205.brushbuddy.board.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a205.brushbuddy.board.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearchCustom {
    //	@NonNull Page<Board> findByDraft_DraftId(Long draftId, Pageable pageable);
    Page<Board> findAllByBoardIsDeletedFalse(Pageable pageable); // 검색 키워드 없이접속

    Optional<Board> findByBoardIdAndBoardIsDeletedFalse(Long boardId);
}
