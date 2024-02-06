package com.a205.brushbuddy.mileage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a205.brushbuddy.mileage.dto.request.MileageSpendRequestDto;
import com.a205.brushbuddy.mileage.service.MileageSpendService;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/api/v1/milage")
@Transactional
public class MileageSpendController {
	@Setter
	private MileageSpendService mileageSpendService;

	// 사용 로그 생성
	@PostMapping("/spend")
	public ResponseEntity<?> spendMileage(MileageSpendRequestDto mileageSpendRequestDto) {
		mileageSpendService.spendMileage(mileageSpendRequestDto);
		return ResponseEntity.ok().build();
	}
}
