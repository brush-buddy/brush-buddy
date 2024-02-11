package com.a205.brushbuddy.pay.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.mileage.repository.MileageRepository;
import com.a205.brushbuddy.pay.dto.request.KakaopayApproveRequestDto;
import com.a205.brushbuddy.pay.dto.request.KakaopayReadyRequestDto;
import com.a205.brushbuddy.pay.dto.response.KakaopayApproveResponseDto;
import com.a205.brushbuddy.pay.dto.response.KakaopayReadyResponseDto;
import com.a205.brushbuddy.pay.service.KakaopayService;
import com.a205.brushbuddy.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO : KakaopayReadyRequestDto의 URL(approvalUrl, cancelUrl, failUrl)과 redirectUrl 등 완성 프론트 URL 및 카카오API 연동 필요

@Slf4j
@RestController("/api/v1/pay")
@RequiredArgsConstructor
public class KakaopayController {
    private final JwtUtil jwtUtil;
    private final KakaopayService kakaopayService;
    private final MileageRepository mileageRepository;
    //// 브러쉬버디 충전 내역용 workplaceid = 0, 갱신 필요시 참고
    @Value("${spring.kakao.pay.workplaceid}")
    private int workplaceId;
    // ApproveRequest에 필요한 변수만 선언
    private int mileageAmount;
    private String partnerOrderId;
    private String partnerUserId;
    private String redirectUrl;
    private String tid;

    // 결제 요청 시작
    @PostMapping
    @ResponseBody
    public KakaopayReadyResponseDto ready(KakaopayReadyRequestDto kakaopayReadyRequestDto) {
        kakaopayService.setKakaopayReadyRequestDto(kakaopayReadyRequestDto);
        mileageAmount = kakaopayReadyRequestDto.getTotalAmount();
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
    public String completed(@RequestParam("pg_token") String pgToken, HttpServletRequest request) {
        KakaopayApproveRequestDto kakaopayApproveRequestDto = new KakaopayApproveRequestDto();
        kakaopayApproveRequestDto.setTid(tid);
        kakaopayApproveRequestDto.setPartnerOrderId(partnerOrderId);
        kakaopayApproveRequestDto.setPartnerOrderId(partnerUserId);
        kakaopayApproveRequestDto.setPgToken(pgToken);
        kakaopayService.setKakaopayApproveRequestDto(kakaopayApproveRequestDto);

        KakaopayApproveResponseDto kakaopayApproveResponseDto = kakaopayService
            .sendApproveRequest(kakaopayApproveRequestDto);
        kakaopayService.setKakaopayApproveResponseDto(kakaopayApproveResponseDto);

        Integer userId = jwtUtil.getUserId(request).orElseThrow(() -> new BaseException(ErrorCode.UNAUTHORIZED));
        mileageRepository.updateMileageByUser(userId, mileageAmount);

        return "redirect:" + redirectUrl;
    }
}
