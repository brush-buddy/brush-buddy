package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.*;
import com.a205.brushbuddy.user.domain.User;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;


@RequiredArgsConstructor
public class HeartRepositoryCustomImpl implements HeartRepositoryCustom{

    private final JPAQueryFactory query;
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

    @Override
    public Page<Board> getHeartBoardList(Integer userId, String search, Pageable pageable) {
        QBoard b = QBoard.board;
        QHeart h = QHeart.heart;
        List<Board> result;
        JPQLQuery<Board> count;
        // 개수 세기

        if(search == null || search.isEmpty()){
            result = query.select(b)
                    .from(h)
                    .join(b)
                    .on(h.heartId.board.boardId.eq(b.boardId))
                    .where(h.heartId.user.userId.eq(userId))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = query.select(b)
                    .from(h)
                    .join(b)
                    .on(h.heartId.board.boardId.eq(b.boardId))
                    .where(h.heartId.user.userId.eq(userId));
        }else{
            result = query.select(b)
                    .from(h)
                    .join(b)
                    .on(h.heartId.board.boardId.eq(b.boardId))
                    .where(h.heartId.user.userId.eq(userId))
                    .where(h.heartId.board.boardContent.contains(search).or(h.heartId.board.boardTitle.contains(search)))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = query.select(b)
                    .from(h)
                    .join(b)
                    .on(h.heartId.board.boardId.eq(b.boardId))
                    .where(h.heartId.user.userId.eq(userId))
                    .where(h.heartId.board.boardContent.contains(search).or(h.heartId.board.boardTitle.contains(search)));

        }

        return PageableExecutionUtils.getPage(result, pageable, count::fetchCount);
    }
}
