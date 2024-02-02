package com.a205.brushbuddy.mypage.controller;


import com.a205.brushbuddy.mypage.dto.request.MypageBookmarkedDraftListRequestDto;
import com.a205.brushbuddy.mypage.dto.request.MypageGeneratedDraftListRequestDto;
import com.a205.brushbuddy.mypage.dto.request.MypageHeartBoardListRequestDto;
import com.a205.brushbuddy.mypage.dto.request.MypagePurchasedDraftListRequestDto;
import com.a205.brushbuddy.mypage.dto.response.MypageBookmarkedDraftListResponseDto;
import com.a205.brushbuddy.mypage.dto.response.MypageGeneratedDraftListResponseDto;
import com.a205.brushbuddy.mypage.dto.response.MypageHeartBoardListResponseDto;
import com.a205.brushbuddy.mypage.dto.response.MypagePurchasedDraftListResponseDto;
import com.a205.brushbuddy.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/mypage")
@RequiredArgsConstructor
public class MypageController {
    private final MypageService mypageService;

    // 좋아요 누른 게시글 리스트 조회
    @GetMapping("/heart/list")
    public ResponseEntity<?> getHeartList(MypageHeartBoardListRequestDto requestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        Pageable pageable  = PageRequest.of(requestDto.getPageNum() - 1,
                requestDto.getListNum(),
                requestDto.getDirection(),
                requestDto.getOrder());
        MypageHeartBoardListResponseDto dto = mypageService.getHeartBoardList(userId, requestDto.getSearch(), pageable);
        return  ResponseEntity.ok(dto);
    }

    //생성한 도안 리스트 조회
    @GetMapping("/generate/list")
    public ResponseEntity<?> getGeneratedList(MypageGeneratedDraftListRequestDto requestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        Pageable pageable  = PageRequest.of(requestDto.getPageNum() - 1,
                requestDto.getListNum(),
                requestDto.getDirection(),
                requestDto.getOrder());
        MypageGeneratedDraftListResponseDto dto = mypageService.getGeneratedBoardList(userId, requestDto.getSearch(), pageable);
        return  ResponseEntity.ok(dto);
    }

    //북마크한 도안 리스트 조회
    @GetMapping("/bookmarks/list")
    public ResponseEntity<?> getBookmarkedList(MypageBookmarkedDraftListRequestDto requestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        Pageable pageable  = PageRequest.of(requestDto.getPageNum() - 1,
                requestDto.getListNum(),
                requestDto.getDirection(),
                requestDto.getOrder());
        MypageBookmarkedDraftListResponseDto dto = mypageService.getBookmarkedBoardList(userId, requestDto.getSearch(), pageable);
        return  ResponseEntity.ok(dto);
    }

    //구매한 도안 내역 리스트 조회
    @GetMapping("/payments/list")
    public ResponseEntity<?> getPaymentsList(MypagePurchasedDraftListRequestDto requestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        Pageable pageable  = PageRequest.of(requestDto.getPageNum() - 1,
                requestDto.getListNum(),
                requestDto.getDirection(),
                requestDto.getOrder());
        MypagePurchasedDraftListResponseDto dto = mypageService.getPurchasedDraftList(userId, requestDto.getSearch(), pageable);
        return  ResponseEntity.ok(dto);
    }
}
