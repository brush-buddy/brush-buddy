package com.a205.brushbuddy.pay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.a205.brushbuddy.pay.dto.request.KakaopayApproveRequest;
import com.a205.brushbuddy.pay.dto.request.KakaopayReadyRequest;
import com.a205.brushbuddy.pay.dto.request.KakaopayRequestHeader;
import com.a205.brushbuddy.pay.dto.response.KakaopayApproveResponse;
import com.a205.brushbuddy.pay.dto.response.KakaopayReadyResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
//// interface PayService 필요 여부
public class KakaopayService {
	private static final HttpHeaders headers = KakaopayRequestHeader.getHeaders();

	@Value("${kakaopay.ready.request.url}")
	private static String readyRequestUrl;
	@Value("${kakaopay.ready.request.size}")
	private static int readyRequestSize;
	@Value("${kakopay.ready.request.name}")
	private static String[] readyRequestName;

	@Value("${kakaopay.approve.request.url}")
	private static String approveRequestUrl;
	@Value("${kakaopay.approve.request.size}")
	private static int approveRequestSize;
	@Value("${kakaopay.approve.request.name}")
	private static String[] approveRequestName;

	@Getter
	private KakaopayReadyRequest kakaopayReadyRequest;
	@Getter
	private KakaopayReadyResponse kakaopayReadyResponse;
	@Getter
	private KakaopayApproveRequest kakaopayApproveRequest;
	@Getter
	private KakaopayApproveResponse kakaopayApproveResponse;

	public KakaopayReadyResponse sendReadyRequest(KakaopayReadyRequest kakaopayReadyRequest)
	// (String cid, String partnerOrderId, String partnerUserId, String itemName, int quantity, int totalAmount, int taxFreeAmount, String approvalUrl, String cancelUrl, String failUrl)
	{
		this.kakaopayReadyRequest = kakaopayReadyRequest;
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

		log.info("sendReadyRequest HTTP Entitiy body :");
		String[] value = kakaopayReadyRequest.getAll();
		for (int i = 0; i < readyRequestSize; ++i) {
			body.add(readyRequestName[i], value[i]);
			log.info(readyRequestName[i] + ":" + value[i]);
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
			body.add(approveRequestName[i], value[i]);
		}

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(body,
			headers);

		RestTemplate restTemplate = new RestTemplate();

		kakaopayApproveResponse = restTemplate.postForObject(approveRequestUrl, httpEntity,
			KakaopayApproveResponse.class);


		return kakaopayApproveResponse;
	}
}
