package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Page<Reply> findAllByBoard_BoardId(Long boardId, Pageable pageable); // 댓글 불러오기
}
