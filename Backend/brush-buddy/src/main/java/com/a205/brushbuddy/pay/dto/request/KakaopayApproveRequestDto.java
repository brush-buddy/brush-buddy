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
    public static final int size = 5;
    public static final String[] name = {"cid", "tid", "partner_order_id", "artner_user_id", "pg_token"};
    @Value("${spring.kakao.pay.cid-key}")
    private String cid;
    private String tid;
    private String partnerOrderId;
    private String partnerUserId;
    private String pgToken;

    public String[] getValue() {
        return new String[] {cid, tid, partnerOrderId, partnerUserId, pgToken};
    }
}
