package com.a205.brushbuddy.pay.dto.response;

import java.sql.Timestamp;

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
public class KakaopayApproveResponseDto {
    public static final int size = 15;
    public static final String[] name = {"aid", "tid", "cid", "sid", "partner_order_id", "partner_user_id",
        "payment_method_type", "amount",
        "card_info", "item_name", "item_code", "quantity", "created_at", "approved_at", "payloa"};

    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String partnerOrderId;
    private String partnerUserId;
    private String paymentMethodType;
    private KakaopayAmountDto amount;
    private KakaopayCardInfoDto cardInfo;
    private String itemName;
    private String itemCode;
    private String quantity;
    private Timestamp createdAt;
    private Timestamp approvedAt;
    private String payload;
}
