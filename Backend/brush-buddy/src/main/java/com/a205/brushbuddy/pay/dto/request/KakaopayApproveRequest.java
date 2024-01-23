package com.a205.brushbuddy.pay.dto.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-request-body
 */
@Slf4j
@Data
public class KakaopayApproveRequest {
	private String cid;
	private String tid;
	private String partnerOrderId;
	private String partnerUserId;
	private String pgToken;
}
