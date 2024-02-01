package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.QBoard;
import com.a205.brushbuddy.board.domain.QHashtag;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

@RequiredArgsConstructor
public class BoardSearchCustomImpl implements BoardSearchCustom{
    private final JPAQueryFactory query;

    @Override
    public Page<Board> getSearchList(String search, Pageable pageable) {
        QBoard b = QBoard.board;
        QHashtag h = QHashtag.hashtag;

        List<Board> result =  query.select(b)
                .from(b)
                .join(h)
                .on(b.boardId.eq(h.id.board.boardId))
                .where(h.id.hashtagContent.contains(search))
                .where(b.boardIsDeleted.eq(false)) // 삭제 안된거로 가져온다.
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPQLQuery<Board> count = query.select(b)
                .join(h)
                .on(b.boardId.eq(h.id.board.boardId))
                .where(h.id.hashtagContent.contains(search))
                .where(b.boardIsDeleted.eq(false)); // 삭제 안된거로 가져온다.

        return PageableExecutionUtils.getPage(result,pageable,count::fetchCount);
    }
}
