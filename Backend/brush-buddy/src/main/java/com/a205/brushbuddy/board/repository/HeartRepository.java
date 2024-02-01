package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Heart;
import com.a205.brushbuddy.board.domain.HeartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, HeartId>, HeartRepositoryCustom {
    public Optional<Heart> findByHeartId_User_UserIdAndHeartId_Board_BoardId(Integer heartId, Long boardId);
}
