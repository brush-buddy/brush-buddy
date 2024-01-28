package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.QHeart;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class HeartRepositoryCustomImpl implements HeartRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public boolean insertHeart(Integer userId, Long boardId){
        QHeart heart = QHeart.heart;
        query.insert(heart)
                .columns(heart.heartId.user, heart.heartId.board)
                .values(userId, boardId);
        return true;
    }
}
