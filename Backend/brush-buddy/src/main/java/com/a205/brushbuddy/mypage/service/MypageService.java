package com.a205.brushbuddy.mypage.service;

import com.a205.brushbuddy.mypage.dto.response.*;
import org.springframework.data.domain.Pageable;


public interface MypageService {
    MypageHeartBoardListResponseDto getHeartBoardList(Integer userId, String search, Pageable pageable); // 좋아요 누른 게시글 리스트 조회
    MypageMyBoardListResponseDto getMyBoardList(Integer userId, String search, Pageable pageable); // 내가 쓴 게시글 리스트 조회
    MypageGeneratedDraftListResponseDto getGeneratedBoardList(Integer userId, String search, Pageable pageable); // 생성한 도안 리스트 조회
    MypageBookmarkedDraftListResponseDto getBookmarkedBoardList(Integer userId, String search, Pageable pageable);// 북마크한 도안 리스트 조회
    MypagePurchasedDraftListResponseDto getPurchasedDraftList(Integer userId, String search, Pageable pageable);//구매한 도안 내역 리스트 조회
    boolean modifyNickname(Integer userId, String newNickname); // 닉네임 수정
}
