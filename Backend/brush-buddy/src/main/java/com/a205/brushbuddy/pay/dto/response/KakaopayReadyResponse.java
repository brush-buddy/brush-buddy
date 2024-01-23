package com.a205.brushbuddy.pay.dto.response;

import java.sql.Timestamp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author SSAFY
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-response-body
 */
@Slf4j
@Data
public class KakaopayReadyResponse {
	private String tid;
	private String nextRediretAppUrl;
	private String nextRedirectMobileUrl;
	private String nextRedirectPcUrl;
	private String androidAppScheme;
	private String iosAppScheme;
	private Timestamp createdAt;
}
