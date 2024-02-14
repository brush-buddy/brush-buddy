package com.a205.brushbuddy.pay.service;

import com.a205.brushbuddy.pay.client.PayClient;
import com.amazonaws.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.a205.brushbuddy.pay.dto.request.KakaopayApproveRequestDto;
import com.a205.brushbuddy.pay.dto.request.KakaopayReadyRequestDto;
import com.a205.brushbuddy.pay.dto.response.KakaopayApproveResponseDto;
import com.a205.brushbuddy.pay.dto.response.KakaopayReadyResponseDto;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaopayService {
    private static final String[] headerKey = {"Authorization", "Content-type"};
    private static final String[] headerValue = {"KakaoAK ", "application/x-www-form-urlencoded;charset=utf-8"};
    private HttpHeaders headers;
    @Value("${spring.kakao.admin-key}")
    private String kakaoAdminKey;
    @Value("${spring.kakao.pay.ready.request.url}")
    private String readyRequestUrl;
    @Value("${spring.kakao.pay.ready.request.approval}")
    private String approveRequestUrl;
    @Getter
    @Setter
    private KakaopayReadyRequestDto kakaopayReadyRequestDto;
    @Getter
    @Setter
    private KakaopayReadyResponseDto kakaopayReadyResponseDto;
    @Getter
    @Setter
    private KakaopayApproveRequestDto kakaopayApproveRequestDto;
    @Getter
    @Setter
    private KakaopayApproveResponseDto kakaopayApproveResponseDto;

    private final PayClient payClient;
    @PostConstruct
    public void setHeader() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.set(headerKey[0], headerValue[0] + kakaoAdminKey);
        headers.set(headerKey[1], headerValue[1]);
        this.headers = new HttpHeaders(headers);
    }

    public KakaopayReadyResponseDto sendReadyRequest(KakaopayReadyRequestDto kakaopayReadyRequestDto) {
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//
//        log.info("sendReadyRequest HTTP Entitiy body :");
//        String[] value = kakaopayReadyRequestDto.getValue();
//        for (int i = 0; i < KakaopayReadyRequestDto.size; ++i) {
//            body.add(KakaopayReadyRequestDto.name[i], value[i]);
//            log.info(KakaopayReadyRequestDto.name[i] + ":" + value[i]);
//        }
//
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(body,
//            headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        kakaopayReadyResponseDto = restTemplate.postForObject(readyRequestUrl, httpEntity,
//            KakaopayReadyResponseDto.class);
//
//        return kakaopayReadyResponseDto;
        return null;
    }

    public KakaopayApproveResponseDto sendApproveRequest(KakaopayApproveRequestDto kakaopayApproveRequestDto) {
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//
//        String[] value = kakaopayApproveRequestDto.getValue();
//        for (int i = 0; i < KakaopayApproveRequestDto.size; ++i) {
//            body.add(KakaopayApproveRequestDto.name[i], value[i]);
//            log.info(KakaopayApproveRequestDto.name[i] + ":" + value[i]);
//        }
//
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(body,
//            headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        kakaopayApproveResponseDto = restTemplate.postForObject(approveRequestUrl, httpEntity,
//            KakaopayApproveResponseDto.class);
//
//        return kakaopayApproveResponseDto;
        return null;
    }

    public ResponseEntity<KakaopayReadyResponseDto> sendApprove(KakaopayApproveRequestDto kakaopayApproveRequestDto) {
        return payClient.makePayment(kakaopayApproveRequestDto);
    }

    public ResponseEntity<?> sendComplete(KakaopayReadyRequestDto kakaopayReadyRequestDto) {
        return payClient.savePayment(kakaopayReadyRequestDto);
    }
}
