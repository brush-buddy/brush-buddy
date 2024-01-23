package com.a205.brushbuddy.pay.dto.response;

import java.sql.Timestamp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-response-body
 */
@Slf4j
@Data
public class KakaopayApproveResponse {
	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partnerOrderId;
	private String partnerUserId;
	private String paymentMethodType;
	private KakaopayAmount amount;
	private KakaopayCardInfo cardInfo;
	private String itemName;
	private String itemCode;
	private String quantity;
	private Timestamp createdAt;
	private Timestamp approvedAt;
	private String payload;
}
