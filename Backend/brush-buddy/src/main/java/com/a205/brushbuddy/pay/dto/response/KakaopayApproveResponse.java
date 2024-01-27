package com.a205.brushbuddy.pay.dto.response;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-response-body
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

	@Value("${kakaopay.approve.response.size}")
	public static int size;

	@Value("${kakaopay.approve.response.name}")
	public static String[] name;
}
