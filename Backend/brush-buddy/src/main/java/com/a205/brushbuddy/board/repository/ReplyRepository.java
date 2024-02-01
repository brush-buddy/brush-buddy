package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Page<Reply> findAllByBoard_BoardIdAndBoard_BoardIsDeletedFalseAndReplyIsDeletedFalse(Long boardId, Pageable pageable); // 댓글 불러오기
    Optional<Reply> findByIdAndReplyIsDeletedFalse(Long id);
}
