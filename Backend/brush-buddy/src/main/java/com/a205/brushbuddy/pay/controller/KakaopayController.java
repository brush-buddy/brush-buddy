package com.a205.brushbuddy.pay.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.a205.brushbuddy.mileage.repository.InsertMileageRepository;
import com.a205.brushbuddy.pay.dto.request.KakaopayApproveRequestDto;
import com.a205.brushbuddy.pay.dto.request.KakaopayReadyRequestDto;
import com.a205.brushbuddy.pay.dto.response.KakaopayApproveResponseDto;
import com.a205.brushbuddy.pay.dto.response.KakaopayReadyResponseDto;
import com.a205.brushbuddy.pay.service.KakaopayService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

// TODO : KakaopayReadyRequestDto의 URL(approvalUrl, cancelUrl, failUrl)과 redirectUrl 등 완성 프론트 URL 및 카카오API 연동 필요

@Slf4j
@RestController
public class KakaopayController {
	@Value("${spring.kakao.pay.datetimeformatter.ofpattern}")
	private DateTimeFormatter dateTimeFormatter;

	//// 브러쉬버디 충전 내역용 workplaceid = 0, 갱신 필요시 참고
	@Value("${spring.kakao.pay.workplaceid}")
	private int workplaceId;

	@Setter
	private KakaopayService kakaopayService;
	@Setter
	private InsertMileageRepository insertMileageRepository;

	// ApproveRequest에 필요한 변수만 선언
	private String itemName;
	private String partnerOrderId;
	private String partnerUserId;
	private String redirectUrl;
	private String tid;

	// 결제 요청 시작
	@PostMapping("${spring.kakao.pay.mapping}")
	@ResponseBody
	public KakaopayReadyResponseDto ready(KakaopayReadyRequestDto kakaopayReadyRequestDto) {
		kakaopayService.setKakaopayReadyRequestDto(kakaopayReadyRequestDto);
		itemName = kakaopayReadyRequestDto.getItemName();
		partnerOrderId = kakaopayReadyRequestDto.getPartnerOrderId();
		partnerUserId = kakaopayReadyRequestDto.getPartnerUserId();
		redirectUrl = kakaopayReadyRequestDto.getRedirectUrl();
		KakaopayReadyResponseDto kakaopayReadyResponseDto = kakaopayService.sendReadyRequest(kakaopayReadyRequestDto);
		tid = kakaopayReadyResponseDto.getTid();
		kakaopayService.setKakaopayReadyResponseDto(kakaopayReadyResponseDto);

		return kakaopayReadyResponseDto;
	}

	// 결제 완료 이후
	@GetMapping("${spring.kakao.pay.ready.request.approval}")
	public String completed(@RequestParam("pg_token") String pgToken) {
		KakaopayApproveRequestDto kakaopayApproveRequestDto = new KakaopayApproveRequestDto();
		kakaopayApproveRequestDto.setTid(tid);
		kakaopayApproveRequestDto.setPartnerOrderId(partnerOrderId);
		kakaopayApproveRequestDto.setPartnerOrderId(partnerUserId);
		kakaopayApproveRequestDto.setPgToken(pgToken);
		kakaopayService.setKakaopayApproveRequestDto(kakaopayApproveRequestDto);

		KakaopayApproveResponseDto kakaopayApproveResponseDto = kakaopayService
				.sendApproveRequest(kakaopayApproveRequestDto);
		kakaopayService.setKakaopayApproveResponseDto(kakaopayApproveResponseDto);

		// User API, user.userMileage 처리 필요
		// 1. 유저 테이블의 유저 정보 메모리 적재
		// 2. 유저 마일리지 업데이트
		// 3. 마일리지 테이블 인서트
//		User user =
//		insertMileageRepository.insertChargedMileage(user.getUserId, workplaceId, mileageBefore, mileageAfter, mileageAmount, itemName);

		return "redirect:" + redirectUrl;
	}
}
