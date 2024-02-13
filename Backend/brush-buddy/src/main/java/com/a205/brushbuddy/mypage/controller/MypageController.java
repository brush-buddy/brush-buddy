package com.a205.brushbuddy.mypage.controller;


import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.mypage.dto.request.*;
import com.a205.brushbuddy.mypage.dto.response.*;
import com.a205.brushbuddy.mypage.service.MypageService;
import com.a205.brushbuddy.util.JwtUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/mypage")
@RequiredArgsConstructor
public class MypageController {
    private final MypageService mypageService;
    private final JwtUtil jwtUtil;

    // 좋아요 누른 게시글 리스트 조회
    @GetMapping("/heart/list")
    public ResponseEntity<?> getHeartList(MypageHeartBoardListRequestDto requestDto, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        Pageable pageable  = PageRequest.of(requestDto.getPageNum() - 1,
                requestDto.getListNum(),
                requestDto.getDirection(),
                requestDto.getOrder());
        MypageHeartBoardListResponseDto dto = mypageService.getHeartBoardList(userId, requestDto.getSearch(), pageable);
        return  ResponseEntity.ok(dto);
    }

    //내가 쓴 게시글 조회
    @GetMapping("/myboard/list")
    public ResponseEntity<?> getMyBoardList(MypageMyBoardListRequestDto requestDto, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        Pageable pageable  = PageRequest.of(requestDto.getPageNum() - 1,
                requestDto.getListNum(),
                requestDto.getDirection(),
                requestDto.getOrder());
        MypageMyBoardListResponseDto dto = mypageService.getMyBoardList(userId, requestDto.getSearch(), pageable);
        return  ResponseEntity.ok(dto);
    }

    //생성한 도안 리스트 조회
    @GetMapping("/generate/list")
    public ResponseEntity<?> getGeneratedList(MypageGeneratedDraftListRequestDto requestDto, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        Pageable pageable  = PageRequest.of(requestDto.getPageNum() - 1,
                requestDto.getListNum(),
                requestDto.getDirection(),
                requestDto.getOrder());
        MypageGeneratedDraftListResponseDto dto = mypageService.getGeneratedBoardList(userId, requestDto.getSearch(), pageable);
        return  ResponseEntity.ok(dto);
    }

    //북마크한 도안 리스트 조회
    @GetMapping("/bookmarks/list")
    public ResponseEntity<?> getBookmarkedList(MypageBookmarkedDraftListRequestDto requestDto, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        Pageable pageable  = PageRequest.of(requestDto.getPageNum() - 1,
                requestDto.getListNum(),
                requestDto.getDirection(),
                requestDto.getOrder());
        MypageBookmarkedDraftListResponseDto dto = mypageService.getBookmarkedBoardList(userId, requestDto.getSearch(), pageable);
        return  ResponseEntity.ok(dto);
    }

    //구매한 도안 내역 리스트 조회
    @GetMapping("/payments/list")
    public ResponseEntity<?> getPaymentsList(MypagePurchasedDraftListRequestDto requestDto, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        Pageable pageable  = PageRequest.of(requestDto.getPageNum() - 1,
                requestDto.getListNum(),
                requestDto.getDirection(),
                requestDto.getOrder());
        MypagePurchasedDraftListResponseDto dto = mypageService.getPurchasedDraftList(userId, requestDto.getSearch(), pageable);
        return  ResponseEntity.ok(dto);
    }

    @PostMapping("/nickname")
    public ResponseEntity<?> modifyNickname(@RequestBody MypageModifyNicknameRequestDto requestDto, HttpServletRequest request){
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        boolean result = mypageService.modifyNickname(userId, requestDto.getNewNickname());
        if (result) {
            return ResponseEntity.ok("Nickname successfully modified");
        } else {
            throw new BaseException(ErrorCode.BAD_REQUEST);
        }
    }
}
