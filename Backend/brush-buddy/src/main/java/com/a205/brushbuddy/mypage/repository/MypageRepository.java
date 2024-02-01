package com.a205.brushbuddy.mypage.repository;


import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.draft.domain.Draft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MypageRepository {
    Page<Board> getHeartBoardList(Integer userId, String search, Pageable pageable); // 하트한 보드 리스트 반환
    Page<Draft> getGeneratedDraftList(Integer userId, String search, Pageable pageable); // 생성한 도안 리스트 반환
    Page<Draft> getBookmarkedDraftList(Integer userId,String search, Pageable pageable); // 북마크한 도안 리스트 반환
    Page<Draft> getPurchasedDraftList(Integer userId,String search, Pageable pageable); // 구매한 리스트 반환
}
