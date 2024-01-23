package com.a205.brushbuddy.pay.dto.response;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-response-body-card-info
 * KakaopayApproveResponse에서 사용
 */
@Slf4j
@Data
public class KakaopayCardInfo {
	private String kakaopayPurchaseCorp;
	private String kakaopayPurchaseCorpCode;
	private String kakaopayIssuerCorp;
	private String kakaopayissuerCorpCode;
	private String bin;
	private String cardType;
	private String installMonth;
	private String approvedId;
	private String cardMid;
	private String interestFreeInstall;
	private String installmentType;
	private String cardItemCode;
}
