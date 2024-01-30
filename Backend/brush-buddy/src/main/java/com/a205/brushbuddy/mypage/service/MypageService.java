package com.a205.brushbuddy.mypage.service;

import java.awt.print.Pageable;

public interface MypageService {
   void getHeartBoardList(Integer userId, Pageable pageable); // 좋아요 누른 게시글 리스트 조회
   void getGeneratedBoardList(Integer userId, Pageable pageable); // 생성한 도안 리스트 조회

    void getBookmarkedBoardList(Integer userId, Pageable pageable);// 북마크한 도안 리스트 조회

    void getPurchasedDraftList(Integer userId, Pageable pageable);//구매한 도안 내역 리스트 조회
}
