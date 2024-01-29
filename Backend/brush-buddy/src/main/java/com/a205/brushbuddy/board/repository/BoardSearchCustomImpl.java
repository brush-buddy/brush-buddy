package com.a205.brushbuddy.board.repository;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.QBoard;
import com.a205.brushbuddy.board.domain.QHashtag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class BoardSearchCustomImpl implements BoardSearchCustom{
    private final JPAQueryFactory query;

    @Override
    public List<Board> getSearchList(String search, Pageable pageable) {
        QBoard b = QBoard.board;
        QHashtag h = QHashtag.hashtag;

        List<Board> board = query.select(b)
                .from(b)
                .join(h)
                .on(b.boardId.eq(h.id.board.boardId))
                .where(h.id.hashtagContent.eq(search))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return board;
    }
}
