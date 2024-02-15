package com.a205.brushbuddy.pay.controller;

import com.a205.brushbuddy.mileage.domain.MileageLog;
import com.a205.brushbuddy.mileage.repository.MileageLogRepository;
import com.a205.brushbuddy.mileage.service.MileageService;
import com.a205.brushbuddy.pay.client.PayClient;
import com.a205.brushbuddy.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

// TODO : KakaopayReadyRequestDto의 URL(approvalUrl, cancelUrl, failUrl)과 redirectUrl 등 완성 프론트 URL 및 카카오API 연동 필요

@Slf4j
@RestController
@RequestMapping("/api/v1/pay")
@RequiredArgsConstructor
public class KakaopayController {
    private final JwtUtil jwtUtil;
    private final KakaopayService kakaopayService;
    private final MileageRepository mileageRepository;
    private final MileageService mileageService;
    private final UserRepository userRepository;
    //// 브러쉬버디 충전 내역용 workplaceid = 0, 갱신 필요시 참고
    @Value("${spring.kakao.pay.workplaceid}")
    private int workplaceId;
    // ApproveRequest에 필요한 변수만 선언
    private int mileageAmount;
    private String partnerOrderId;
    private String partnerUserId;
    private String redirectUrl;
    private String tid;
    private RestTemplate restTemplate;


    // 결제 요청 시작
    @PostMapping("")
    @ResponseBody
    public KakaopayReadyResponseDto ready(KakaopayReadyRequestDto kakaopayReadyRequestDto) {
//        kakaopayService.setKakaopayReadyRequestDto(kakaopayReadyRequestDto);
//        mileageAmount = kakaopayReadyRequestDto.getTotalAmount();
//        partnerOrderId = kakaopayReadyRequestDto.getPartnerOrderId();
//        partnerUserId = kakaopayReadyRequestDto.getPartnerUserId();
//        redirectUrl = kakaopayReadyRequestDto.getRedirectUrl();
//        KakaopayReadyResponseDto kakaopayReadyResponseDto = kakaopayService.sendReadyRequest(kakaopayReadyRequestDto);
//        tid = kakaopayReadyResponseDto.getTid();
//        kakaopayService.setKakaopayReadyResponseDto(kakaopayReadyResponseDto);
//
//        return kakaopayReadyResponseDto;
        return null;
    }

    // 결제 완료 이후
//    @GetMapping("${spring.kakao.pay.ready.request.approval}")
//    public String completed(@RequestParam("pg_token") String pgToken, HttpServletRequest request) {
//        KakaopayApproveRequestDto kakaopayApproveRequestDto = new KakaopayApproveRequestDto();
//        kakaopayApproveRequestDto.setTid(tid);
//        kakaopayApproveRequestDto.setPartnerOrderId(partnerOrderId);
//        kakaopayApproveRequestDto.setPartnerOrderId(partnerUserId);
//        kakaopayApproveRequestDto.setPgToken(pgToken);
//        kakaopayService.setKakaopayApproveRequestDto(kakaopayApproveRequestDto);
//
//        KakaopayApproveResponseDto kakaopayApproveResponseDto = kakaopayService
//            .sendApproveRequest(kakaopayApproveRequestDto);
//        kakaopayService.setKakaopayApproveResponseDto(kakaopayApproveResponseDto);
//
//        Integer userId = jwtUtil.getUserId(request).orElseThrow(() -> new BaseException(ErrorCode.UNAUTHORIZED));
//
//        return "redirect:" + redirectUrl;
//    }


    @PostMapping("/makePayment")
    public ResponseEntity<?> makePayment(HttpServletRequest request, @RequestParam("mileage") int mileage) {

        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(
                        ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        Long mileageNumber = mileageService.requestOrderInfo(mileage, userId);
        if (mileageNumber != null) {
            ResponseEntity<KakaopayReadyResponseDto> payApprove = kakaopayService.sendApprove(
                    KakaopayApproveRequestDto.builder().cid("TC0ONETIME").partner_order_id(mileageNumber.toString())
                            .partner_user_id(String.valueOf(userId)).item_name("mileage").quantity("1")
                            .total_amount(String.valueOf(mileage)).tax_free_amount("0")
                            .approval_url("https://brush-buddy.duckdns.org/pay/success")
                            .cancel_url("https://brush-buddy.duckdns.org/pay/cancel")
                            .fail_url("https://brush-buddy.duckdns.org/pay/fail").build());
            String tid = Objects.requireNonNull(payApprove.getBody()).getTid();
            mileageService.setMileageTid(mileageNumber, tid);
            return ResponseEntity.ok(payApprove);

        }
        throw new BaseException(ErrorCode.NOT_PRIVIEGED);
    }

    @PostMapping("/complete")
    public ResponseEntity<?> makeSave(HttpServletRequest request, @RequestParam("pg_token") String pgToken,@RequestParam("tid")
                                      String tid) {
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(
                        ErrorCode.INVALID_TOKEN));// 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송

        MileageLog mileageLog = mileageService.getMileageLog(tid);

        KakaopayReadyRequestDto kakaopayApproveRequestDto = KakaopayReadyRequestDto.builder().cid("TC0ONETIME").tid(tid)
                .partner_order_id(mileageLog.getMileageLogId().toString()).partner_user_id(String.valueOf(userId))
                .pg_token(pgToken).build();



        ResponseEntity<?> sendData = kakaopayService.sendComplete(kakaopayApproveRequestDto);
        if (sendData.getStatusCodeValue() == 200) {
            mileageService.addMileage(userId, mileageLog.getMileageLogId());
        }

        return ResponseEntity.ok(sendData);
    }

}
