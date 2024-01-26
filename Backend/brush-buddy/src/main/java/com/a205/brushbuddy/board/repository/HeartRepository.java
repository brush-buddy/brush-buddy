package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Heart;
import com.a205.brushbuddy.board.domain.HeartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<Heart, HeartId> {

}
