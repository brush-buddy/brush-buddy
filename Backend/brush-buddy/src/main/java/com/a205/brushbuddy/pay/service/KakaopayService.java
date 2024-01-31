package com.a205.brushbuddy.pay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.a205.brushbuddy.pay.dto.request.KakaopayApproveRequest;
import com.a205.brushbuddy.pay.dto.request.KakaopayReadyRequest;
import com.a205.brushbuddy.pay.dto.response.KakaopayApproveResponse;
import com.a205.brushbuddy.pay.dto.response.KakaopayReadyResponse;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaopayService {
	/*
		private static String kakaoAdminKey;
		private static HttpHeaders headers;
		private static String[] headerKey;
		private static String[] headerValue;
		private static String readyRequestUrl;
		private static int readyRequestSize;
		private static String[] readyRequestName;
		private static String approveRequestUrl;
		private static int approveRequestSize;
		private static String[] approveRequestName;

		@Value("${spring.kakao.admin-key}")
		public static void setkakaoAdminKey(String kakaoAdminKey) {
			KakaopayService.kakaoAdminKey = kakaoAdminKey;
			System.out.println("set admin");
		}

		@Value("${spring.kakao.pay.header.key}")
		public static void setHeaderKey(String[] headerKey) {
			KakaopayService.headerKey = headerKey;
			System.out.println("set key");
		}

		@Value("${spring.kakao.pay.header.value}")
		public static void setHeaderValue(String[] headerValue) {
			KakaopayService.headerValue = headerValue;
			System.out.println("set value");
		}

		@Value("${spring.kakao.pay.ready.request.url}")
		public static void setReadyRequestUrl(String readyRequestUrl) {
			KakaopayService.readyRequestUrl = readyRequestUrl;
		}

		@Value("${spring.kakao.pay.ready.request.size}")
		public static void setReadyRequestSize(int readyRequestSize) {
			KakaopayService.readyRequestSize = readyRequestSize;
		}

		@Value("${spring.kakao.pay.ready.request.name}")
		public static void setReadyRequestName(String[] readyRequestName) {
			KakaopayService.readyRequestName = readyRequestName;
		}

		@Value("${spring.kakao.pay.approve.request.url}")
		public static void setApproveRequestUrl(String approveRequestUrl) {
			KakaopayService.approveRequestUrl = approveRequestUrl;
		}

		@Value("${kakaopay.approve.request.size}")
		public static void setApproveRequestSize(int approveRequestSize) {
			KakaopayService.approveRequestSize = approveRequestSize;
		}

		@Value("${spring.kakao.pay.approve.request.name}")
		public static void setApproveRequestName(String[] approveRequestName) {
			KakaopayService.approveRequestName = approveRequestName;
		}
	*/

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

	private KakaopayReadyRequest kakaopayReadyRequest;
	private KakaopayReadyResponse kakaopayReadyResponse;
	private KakaopayApproveRequest kakaopayApproveRequest;
	private KakaopayApproveResponse kakaopayApproveResponse;

	@PostConstruct
	public void setHeader() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.set(headerKey.get(0), headerValue.get(0) + kakaoAdminKey);
		headers.set(headerKey.get(1), headerValue.get(1));
		headers = new org.springframework.http.HttpHeaders(headers);
	}

	public KakaopayReadyResponse sendReadyRequest(KakaopayReadyRequest kakaopayReadyRequest)
	// (String cid, String partnerOrderId, String partnerUserId, String itemName, int quantity, int totalAmount, int taxFreeAmount, String approvalUrl, String cancelUrl, String failUrl)
	{
		this.kakaopayReadyRequest = kakaopayReadyRequest;
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

		log.info("sendReadyRequest HTTP Entitiy body :");
		String[] value = kakaopayReadyRequest.getAll();
		for (int i = 0; i < readyRequestSize; ++i) {
			body.add(readyRequestName.get(i), value[i]);
			log.info(readyRequestName.get(i) + ":" + value[i]);
		}

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(body,
			headers);

		RestTemplate restTemplate = new RestTemplate();

		kakaopayReadyResponse = restTemplate.postForObject(readyRequestUrl, httpEntity,
			KakaopayReadyResponse.class);

		return kakaopayReadyResponse;
	}

	public KakaopayApproveResponse sendApproveRequest(KakaopayApproveRequest kakaopayApproveRequest)
	// (String cid, String tid, String partnerOrderId, String partnerUserId, String pgToken)
	{
		this.kakaopayApproveRequest = kakaopayApproveRequest;
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

		String[] value = kakaopayApproveRequest.getString();
		for (int i = 0; i < approveRequestSize; ++i) {
			body.add(approveRequestName.get(i), value[i]);
		}

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(body,
			headers);

		RestTemplate restTemplate = new RestTemplate();

		kakaopayApproveResponse = restTemplate.postForObject(approveRequestUrl, httpEntity,
			KakaopayApproveResponse.class);

		return kakaopayApproveResponse;
	}

}
