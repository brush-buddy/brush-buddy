package com.a205.brushbuddy.pay.dto.response;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-response-body
 */

@Data
@Builder
public class KakaopayReadyResponseDto {

    @JsonProperty("tid")
    private String tid;

    // URL 변수 중 필수
    @JsonProperty("tms_result")
    private Boolean tmsResult;

    @JsonProperty("next_redirect_pc_url")
    private String nextRedirectPcUrl;
    // 아래 변수는 필요 이상
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("next_redirect_app_url")
    private String nextRedirectAppUrl;

    @JsonProperty("next_redirect_mobile_url")
    private String nextRedirectMobileUrl;

    @JsonProperty("android_app_scheme")
    private String androidAppScheme;

    @JsonProperty("ios_app_scheme")
    private String iosAppScheme;

}
