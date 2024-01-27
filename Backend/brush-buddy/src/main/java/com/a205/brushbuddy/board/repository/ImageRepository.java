package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByBoard_BoardId(Long boardId);
}
