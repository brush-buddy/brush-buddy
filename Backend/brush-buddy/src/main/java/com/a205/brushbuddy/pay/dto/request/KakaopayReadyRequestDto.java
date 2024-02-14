package com.a205.brushbuddy.pay.dto.request;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

/** @author SSAFY
 *         https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-request-body */

@Builder
@Data
public class KakaopayReadyRequestDto {
    String cid;
    String tid;
    String partner_order_id;
    String partner_user_id;
    String pg_token;
}
