package com.a205.brushbuddy.pay.dto.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
public class KakaopayRequestHeader {
	@Value("${kakaopay.header.size}")
	private static int size;
	@Value("${kakaopay.header.key}")
	private static String[] key;
	@Value("${kakaopay.header.value}")
	private static String[] value;

	public static HttpHeaders getHeaders() {
		if (key == null || value == null) {
			log.error("-----kakao.header null error-----");
			System.err.println("-----kakao.header null error-----");
			return null;
		}
		if (size != key.length || size != value.length) {
			log.error("-----kakao.header size error-----");
			System.err.println("-----kakao.header size error-----");
			return null;
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		for (int i = 0; i < size; ++i) {
			httpHeaders.set(key[i], value[i]);
		}
		return httpHeaders;
	}
}
