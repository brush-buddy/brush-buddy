package com.a205.brushbuddy.mileage.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.mileage.domain.Mileage;
import com.a205.brushbuddy.mileage.dto.request.MileageHistoryReqeustDto;
import com.a205.brushbuddy.mileage.dto.request.MileageSpendRequestDto;
import com.a205.brushbuddy.mileage.dto.response.MileageHistoryResponseDto;
import com.a205.brushbuddy.mileage.service.MileageService;
import com.a205.brushbuddy.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/mileage")
@RequiredArgsConstructor
public class MileageController {
    private final MileageService mileageService;
    private final JwtUtil jwtUtil;

    @PostMapping("/spend")
    public ResponseEntity<?> spendMileage(@RequestBody MileageSpendRequestDto mileageSpendRequestDto,
        HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request).orElseThrow(() -> new BaseException(ErrorCode.UNAUTHORIZED));
        // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        mileageService.spendMileage(userId, mileageSpendRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history")
    public ResponseEntity<MileageHistoryResponseDto> getHistory(MileageHistoryReqeustDto mileageHistoryReqeustDto,
        HttpServletRequest request) {
        Integer userId = jwtUtil.getUserId(request).orElseThrow(() -> new BaseException(ErrorCode.UNAUTHORIZED));
        int listNum = mileageHistoryReqeustDto.getListNum() == 0 ? 10 : mileageHistoryReqeustDto.getListNum();
        int pageNum = mileageHistoryReqeustDto.getPageNum() == 0 ? 1 : mileageHistoryReqeustDto.getPageNum() - 1;
        System.out.println(listNum+","+pageNum);
        Pageable pageable = PageRequest.of(pageNum, listNum);

        Page<Mileage> history = mileageService.getMileageHistory(userId, pageable);
        MileageHistoryResponseDto mileageHistoryResponseDto = MileageHistoryResponseDto.builder()
            .history(history.getContent()
                .stream()
                .map(e -> Mileage.builder()
                    .mileageId(e.getMileageId())
                    .user(e.getUser())
                    .workplaceId(e.getWorkplaceId())
                    .mileageTimestamp(e.getMileageTimestamp())
                    .mileageBefore(e.getMileageBefore())
                    .mileageAfter(e.getMileageAfter())
                    .mileageAmount(e.getMileageAmount())
                    .mileageContent(e.getMileageContent())
                    .build())
                .toList())
            .length(history.getNumberOfElements())
            .pageNum(history.getNumber() + 1)
            .totalPage(history.getTotalPages())
            .build();

        System.out.println(history.getSize());
        history.stream().toList().forEach(e -> System.out.println(e.toString()));

        return new ResponseEntity<MileageHistoryResponseDto>(mileageHistoryResponseDto, HttpStatus.OK);
    }
}
