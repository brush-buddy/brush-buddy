package com.a205.brushbuddy.pay.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-response-body-amount
 * KakaopayApproveResponse에서 사용
 * 필수가 아닌 클래스
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class KakaopayAmountDto {
    private int total;
    private int taxFree;
    private int vat;
    private int point;
    private int discount;
    private int greenDeposit;
}
