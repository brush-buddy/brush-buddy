package com.a205.brushbuddy.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class HeartRepositoryCustomImpl implements HeartRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public boolean insertHeart(Integer userId, Long boardId){
        return true;
    }
}
