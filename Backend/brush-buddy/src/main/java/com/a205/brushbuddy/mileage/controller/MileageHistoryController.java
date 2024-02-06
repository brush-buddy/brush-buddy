package com.a205.brushbuddy.mileage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a205.brushbuddy.mileage.dto.MileageDto;
import com.a205.brushbuddy.mileage.dto.request.MileageHistoryRequestDto;
import com.a205.brushbuddy.mileage.dto.response.MileageHistoryResponseDto;
import com.a205.brushbuddy.mileage.service.MileageHistoryService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/api/v1/milage")
//// 직접 선언, 참조 선언
public class MileageHistoryController {
    @Setter
    private MileageHistoryService mileageHistoryService;

    // 기록 조회
    @GetMapping("/history")
    public ResponseEntity<MileageHistoryResponseDto> getHistory(
        @RequestParam(required = true)
        Long userId,
        @RequestParam(required = false)
        String start,
        @RequestParam(required = false)
        String end) {

        MileageHistoryRequestDto mileageHistoryRequestDto = new MileageHistoryRequestDto();
        mileageHistoryRequestDto.setUserId(userId);

        if (start != null && start.length() == 6) {
            mileageHistoryRequestDto.setStartDate(start);
        }

        if (end != null && end.length() == 6) {
            mileageHistoryRequestDto.setEndDate(end);
        }

        List<MileageDto> history = mileageHistoryService.getMileageList(mileageHistoryRequestDto);
        MileageHistoryResponseDto mileageHistoryResponseDto = new MileageHistoryResponseDto();
        mileageHistoryResponseDto.setHistory(history);
        mileageHistoryResponseDto.setSize(history.size());

        return new ResponseEntity<MileageHistoryResponseDto>(mileageHistoryResponseDto, HttpStatus.OK);
    }
}
