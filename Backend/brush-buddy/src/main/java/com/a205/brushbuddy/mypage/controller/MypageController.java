package com.a205.brushbuddy.mypage.controller;


import com.a205.brushbuddy.draft.service.DraftService;
import com.a205.brushbuddy.palette.domain.Palette;
import com.a205.brushbuddy.palette.dto.PaletteListRequestDto;
import com.a205.brushbuddy.palette.dto.PaletteListResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/mypage")
@RequiredArgsConstructor
public class MypageController {

    // 좋아요 누른 게시글 리스트 조회
    @GetMapping("/heart/list")
    public ResponseEntity<?> getHeartList( ) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;
        draftService.getDraftList();
        return  ResponseEntity.ok(dto);
    }

    //생성한 도안 리스트 조회
    @GetMapping("/generate/list")
    public ResponseEntity<?> getGeneratedList(PaletteListRequestDto requestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;

        return  ResponseEntity.ok(dto);
    }

    //북마크한 도안 리스트 조회
    @GetMapping("/bookmarks/list")
    public ResponseEntity<?> getBookmarkedList(PaletteListRequestDto requestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;

        return  ResponseEntity.ok(dto);
    }

    //구매한 도안 내역 리스트 조회
    @GetMapping("/payments/list")
    public ResponseEntity<?> getPaymentsList(PaletteListRequestDto requestDto) throws Exception{
        //TODO : userId JWT 토큰으로 부터 추출 및 유효성 조사
        Integer userId = 1;

        return  ResponseEntity.ok(dto);
    }
}
