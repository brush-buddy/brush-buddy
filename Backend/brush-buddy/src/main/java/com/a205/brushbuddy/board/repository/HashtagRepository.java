package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Hashtag;
import com.a205.brushbuddy.board.domain.HashtagPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, HashtagPK> {

}
