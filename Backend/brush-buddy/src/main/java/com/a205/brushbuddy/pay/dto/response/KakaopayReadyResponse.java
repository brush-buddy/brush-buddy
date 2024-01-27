package com.a205.brushbuddy.pay.dto.response;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Value;

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
public class KakaopayReadyResponse {
	private String tid;
	// URL 변수 중 필수
	private String nextRedirectPcUrl;
	// 아래 변수는 필요 이상
	private Timestamp createdAt;
	private String nextRediretAppUrl;
	private String nextRedirectMobileUrl;
	private String androidAppScheme;
	private String iosAppScheme;

	@Value("${kakaopay.ready.response.size}")
	public static int size;

	@Value("${kakaopay.ready.response.name}")
	public static String[] name;

	public String[] getAll() {
		return new String[] {tid, nextRediretAppUrl, nextRedirectMobileUrl, nextRedirectPcUrl, androidAppScheme,
			iosAppScheme, createdAt.toString()};
	}
}
