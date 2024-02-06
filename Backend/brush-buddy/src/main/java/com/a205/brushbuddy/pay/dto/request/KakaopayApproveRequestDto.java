package com.a205.brushbuddy.pay.dto.request;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-request-body
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class KakaopayApproveRequestDto {
    @Value("${spring.kakao.pay.cid-key}")
    private String cid;
    private String tid;
    private String partnerOrderId;
    private String partnerUserId;
    private String pgToken;

    @Value("${spring.kakao.approve.request.size}")
    public static int size;

    @Value("${spring.kakao.approve.request.name}")
    public static String[] name;

    public String[] getString() {
        return new String[] {cid, tid, partnerOrderId, partnerUserId, pgToken};
    }
}
