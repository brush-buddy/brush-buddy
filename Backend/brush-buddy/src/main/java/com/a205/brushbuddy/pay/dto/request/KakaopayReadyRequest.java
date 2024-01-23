package com.a205.brushbuddy.pay.dto.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-request-body
 */
@Slf4j
@Data
public class KakaopayReadyRequest {
	private String cid;
	private String partnerOrderId;
	private String partnerUserId;
	private String itemName;
	private int quantity;
	private int totalAmount;
	private int taxFreeAmount = 0;
	private String approvalUrl;
	private String cancelUrl;
	private String failUrl;
}
