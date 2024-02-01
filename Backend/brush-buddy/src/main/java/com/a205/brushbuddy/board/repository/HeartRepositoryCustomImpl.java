package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.Heart;
import com.a205.brushbuddy.board.domain.HeartId;
import com.a205.brushbuddy.user.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class HeartRepositoryCustomImpl implements HeartRepositoryCustom{

//    private final JPAQueryFactory query;
    private final EntityManager em;

    @Override
    public boolean insertHeart(Integer userId, Long boardId){
        em.persist(Heart.builder().heartId(
                HeartId.builder()
                        .board(Board.builder().boardId(boardId).build())
                        .user(User.builder().userId(userId).build())
                        .build()
        ).build());
        em.flush();
        return true;
    }


}
