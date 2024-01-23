package com.a205.brushbuddy.pay.dto.response;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-response-body-amount
 * KakaopayApproveResponse에서 사용
 */
@Slf4j
@Data
public class KakaopayAmount {
	private int total;
	private int taxFree;
	private int vat;
	private int point;
	private int discount;
	private int greenDeposit;
}
