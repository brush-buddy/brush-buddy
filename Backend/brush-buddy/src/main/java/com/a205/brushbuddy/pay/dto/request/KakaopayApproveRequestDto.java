package com.a205.brushbuddy.pay.dto.request;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-request-body
 */


@Data
@Builder
public class KakaopayApproveRequestDto {
    private String cid;
    private String partner_order_id;
    private String partner_user_id;
    private String item_name;
    private String quantity;
    private String total_amount;
    private String tax_free_amount;
    private String approval_url;
    private String cancel_url;
    private String fail_url;
}
