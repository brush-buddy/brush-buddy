package com.a205.brushbuddy.pay.dto.request;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-request-body
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class KakaopayReadyRequest {
	@Value("${kakaopay.cid}")
	private String cid;
	private String partnerOrderId;
	private String partnerUserId;
	private String itemName;
	private int quantity = 1;
	private int totalAmount;
	private int taxFreeAmount = 0;
	@Value("${kakaopay.ready.request.url.approval}")
	private String approvalUrl;
	@Value("${kakaopay.ready.request.url.cancel}")
	private String cancelUrl;
	@Value("${kakaopay.ready.request.url.fail}")
	private String failUrl;

	@Value("${kakaopay.approve.resquest.size}")
	public static int size;

	@Value("${kakaopay.approve.resquest.name}")
	public static String[] name;

	public String[] getAll() {
		return new String[] {cid, partnerOrderId, partnerUserId, itemName, String.valueOf(quantity),
			String.valueOf(totalAmount), String.valueOf(taxFreeAmount), approvalUrl, cancelUrl, failUrl};
	}
}
