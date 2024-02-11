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
    public static final int size = 10;
    public static final String[] name = {"cid", "partnerOrderId", "partnerUserId", "itemName", "quantity",
        "totalAmount", "taxFreeAmount", "approvalUrl", "cancelUrl", "failUrl"};

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
    private String approvalUrl;
    private String cancelUrl;
    private String failUrl;

    public String[] getValue() {
        return new String[] {cid, partnerOrderId, partnerUserId, itemName, String.valueOf(quantity),
            String.valueOf(totalAmount), String.valueOf(taxFreeAmount), approvalUrl, cancelUrl, failUrl};
    }
}
