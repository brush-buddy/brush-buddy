package com.a205.brushbuddy.pay.dto.request;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** @author SSAFY
 *         https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-request-body */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class KakaopayReadyRequestDto {
	@Value("${spring.kakao.pay.cid}")
	private String cid;
	private String partnerOrderId;
	private String partnerUserId;
	private String itemName;
	private int quantity = 1;
	private int totalAmount;
	private int taxFreeAmount = 0;
	//// 충전 페이지를 이전 url 저장
	private String redirectUrl;
	//// 아래의 모든 URL은 Kakao API에 등록 필요
//	@Value("${spring.kakao.pay.ready.request.approval}")
	private String approvalUrl;
//	@Value("${spring.kakao.pay.ready.request.cancel}")
	private String cancelUrl;
//	@Value("${spring.kakao.pay.ready.request.fail}")
	private String failUrl;

	@Value("${spring.kakao.pay.ready.request.size}")
	public static int size;

	@Value("${spring.kakao.pay.ready.request.name}")
	public static String[] name;

	public String[] getAll() {
		return new String[] { cid, partnerOrderId, partnerUserId, itemName, String.valueOf(quantity),
				String.valueOf(totalAmount), String.valueOf(taxFreeAmount), approvalUrl, cancelUrl, failUrl };
	}
}
