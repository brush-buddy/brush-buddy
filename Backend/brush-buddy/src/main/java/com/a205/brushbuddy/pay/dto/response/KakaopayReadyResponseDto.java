package com.a205.brushbuddy.pay.dto.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-response-body
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KakaopayReadyResponseDto {
    public static final int size = 7;
    public static final String[] name = {"tid", "next_redirect_app_url", "next_redirect_mobile_url",
        "next_redirect_pc_url",
        "android_app_scheme", "ios_app_scheme", "created_at"};

    private String tid;
    // URL 변수 중 필수
    private String nextRedirectPcUrl;
    // 아래 변수는 필요 이상
    private Timestamp createdAt;
    private String nextRediretAppUrl;
    private String nextRedirectMobileUrl;
    private String androidAppScheme;
    private String iosAppScheme;

    public String[] getValue() {
        return new String[] {tid, nextRediretAppUrl, nextRedirectMobileUrl, nextRedirectPcUrl, androidAppScheme,
            iosAppScheme, createdAt.toString()};
    }
}
