package com.a205.brushbuddy.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.draft.domain.Draft;

import lombok.NonNull;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	@NonNull Page<Board> findByDraft_DraftId(Long draftId, Pageable pageable);
}
