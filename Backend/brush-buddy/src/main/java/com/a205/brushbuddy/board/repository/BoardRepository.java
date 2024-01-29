package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearchCustom {
//	@NonNull Page<Board> findByDraft_DraftId(Long draftId, Pageable pageable);
	Page<Board> findAll(Pageable pageable); // 검색 키워드 없이접속
}
