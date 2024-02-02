package com.a205.brushbuddy.mypage.repository;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.domain.QBoard;
import com.a205.brushbuddy.board.domain.QHeart;
import com.a205.brushbuddy.draft.domain.*;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MypageRepositoryImpl implements MypageRepository{

    private final JPAQueryFactory query;
//    private final EntityManager em;

    @Override
    public Page<Board> getHeartBoardList(Integer userId, String search, Pageable pageable) {
        QBoard b = QBoard.board;
        QHeart h = QHeart.heart;

        JPQLQuery<Board> myQuery  = query.select(b)
                .from(h)
                .join(b)
                .on(h.heartId.board.boardId.eq(b.boardId))
                .where(h.heartId.user.userId.eq(userId));

        if(search != null && !search.isEmpty()){
            myQuery = myQuery
                    .where(h.heartId.board.boardContent.contains(search).or(h.heartId.board.boardTitle.contains(search)));
        }

        return PageableExecutionUtils.getPage(
                myQuery
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch(),
                pageable,
                myQuery::fetchCount);
    }

    @Override
    public Page<Draft> getGeneratedDraftList(Integer userId, String search, Pageable pageable) {

        QDraft d = QDraft.draft;
        QDraftCategory dc = QDraftCategory.draftCategory;
        QCategory c = QCategory.category;

        JPQLQuery<Draft> myQuery =
                query.select(d)
                .from(d)
                .where(d.user.userId.eq(userId));

        //검색어가 들어갔다면
        if(search != null && !search.isEmpty()){
            myQuery = myQuery
                    .leftJoin(dc)
                    .on(dc.draftCategoryID.draft.draftId.eq(d.draftId)) //
                    .leftJoin(c)
                    .on(c.categoryId.eq(dc.draftCategoryID.category.categoryId)) // 카테고리 가지고 오기
                    .where(c.categoryContent.contains(search));
        }

        return PageableExecutionUtils.getPage(
                myQuery
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch(),
                pageable,
                myQuery::fetchCount);
    }

    @Override
    public Page<Draft> getBookmarkedDraftList(Integer userId, String search, Pageable pageable) {
        QDraft d = QDraft.draft;
        QDraftCategory dc = QDraftCategory.draftCategory;
        QCategory c = QCategory.category;
        QBookmark bm = QBookmark.bookmark;

        JPQLQuery<Draft> myQuery =
                query.select(d)
                        .from(d)
                        .join(bm)
                        .on(d.draftId.eq(bm.bookmarkId.draft.draftId)) // draft로 합쳐서 북마크한 사용자 가져오고
                        .where(bm.bookmarkId.user.userId.eq(userId));

        if(search != null && !search.isEmpty()){
            myQuery = myQuery
                    .leftJoin(dc)
                    .on(dc.draftCategoryID.draft.draftId.eq(d.draftId)) //
                    .leftJoin(c)
                    .on(c.categoryId.eq(dc.draftCategoryID.category.categoryId)) // 카테고리 가지고 오기
                    .where(c.categoryContent.contains(search)); // 검색어 탐색
        }

        return PageableExecutionUtils.getPage(
                myQuery
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch(),
                pageable,
                myQuery::fetchCount);
    }

    @Override
    public Page<Draft> getPurchasedDraftList(Integer userId, String search, Pageable pageable) {
        QDraft d = QDraft.draft;
        QDraftCategory dc = QDraftCategory.draftCategory;
        QCategory c = QCategory.category;
        QPurchase p = QPurchase.purchase;

        JPAQuery<Draft> myQuery =
                query.select(d)
                        .from(d)
                        .join(p) // 구매 도안 테이블 조인
                        .on(d.draftId.eq(p.purchaseId.draft.draftId))
                        .where(p.purchaseId.user.userId.eq(userId));

        if(search != null && !search.isEmpty()){
            myQuery = myQuery
                    .leftJoin(dc)
                    .on(dc.draftCategoryID.draft.draftId.eq(d.draftId)) //
                    .leftJoin(c)
                    .on(c.categoryId.eq(dc.draftCategoryID.category.categoryId)) // 카테고리 가지고 오기
                    .where(c.categoryContent.contains(search)); // 검색어 탐색
        }

        return PageableExecutionUtils.getPage(
                myQuery
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch(),
                pageable,
                myQuery::fetchCount);
    }
}
