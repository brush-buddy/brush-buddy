package com.a205.brushbuddy.pay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
@NoArgsConstructor
@Service
public class KakaopayService {

	private HttpHeaders headers;
	@Value("${spring.kakao.admin-key}")
	private String kakaoAdminKey;
	@Value("${spring.kakao.pay.header.key}")
	private List<String> headerKey;
	@Value("${spring.kakao.pay.header.value}")
	private List<String> headerValue;
	@Value("${spring.kakao.pay.ready.request.url}")
	private String readyRequestUrl;
	@Value("${spring.kakao.pay.ready.request.size}")
	private int readyRequestSize;
	@Value("${spring.kakao.pay.ready.request.name}")
	private List<String> readyRequestName;
	@Value("${spring.kakao.pay.ready.request.approval}")
	private String approveRequestUrl;
	@Value("${spring.kakao.pay.approve.request.size}")
	private int approveRequestSize;
	@Value("${spring.kakao.pay.approve.request.name}")
	private List<String> approveRequestName;

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

	@PostConstruct
	public void setHeader() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.set(headerKey.get(0), headerValue.get(0) + kakaoAdminKey);
		headers.set(headerKey.get(1), headerValue.get(1));
		this.headers = new HttpHeaders(headers);
	}

	public KakaopayReadyResponseDto sendReadyRequest(KakaopayReadyRequestDto kakaopayReadyRequestDto) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

		log.info("sendReadyRequest HTTP Entitiy body :");
		String[] value = kakaopayReadyRequestDto.getAll();
		for (int i = 0; i < readyRequestSize; ++i) {
			body.add(readyRequestName.get(i), value[i]);
			log.info(readyRequestName.get(i) + ":" + value[i]);
		}

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(body,
			headers);

		RestTemplate restTemplate = new RestTemplate();

		kakaopayReadyResponseDto = restTemplate.postForObject(readyRequestUrl, httpEntity, KakaopayReadyResponseDto.class);

		return kakaopayReadyResponseDto;
	}

	public KakaopayApproveResponseDto sendApproveRequest(KakaopayApproveRequestDto kakaopayApproveRequest) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

		String[] value = kakaopayApproveRequest.getString();
		for (int i = 0; i < approveRequestSize; ++i) {
			body.add(approveRequestName.get(i), value[i]);
			log.info(approveRequestName.get(i) + ":" + value[i]);
		}

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(body,
			headers);

		RestTemplate restTemplate = new RestTemplate();

		kakaopayApproveResponseDto = restTemplate.postForObject(approveRequestUrl, httpEntity, KakaopayApproveResponseDto.class);

		return kakaopayApproveResponseDto;
	}

}
