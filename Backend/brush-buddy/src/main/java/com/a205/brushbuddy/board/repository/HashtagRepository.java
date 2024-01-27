package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Hashtag;
import com.a205.brushbuddy.board.domain.HashtagPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, HashtagPK> {
    List<Hashtag> findAllById_Board_BoardId(Long boardId);
}
