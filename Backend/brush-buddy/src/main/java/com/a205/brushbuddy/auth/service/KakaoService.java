package com.a205.brushbuddy.auth.service;


import com.a205.brushbuddy.auth.client.KakaoAuthApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final KakaoAuthApi kakaoAuthApi;

    @Value(value = "${jwt.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value(value = "${jwt.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    @Value(value = "${jwt.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @Value(value = "${jwt.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private String grantType;

    public String getAccessToken(String code){
        return kakaoAuthApi.getAccessToken(clientId,clientSecret,grantType,redirectUri, code).getBody();
    }

    public String getKakaoData(String socialAccessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", socialAccessToken);
            HttpEntity<JsonArray> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<Object> responseData = restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", httpEntity, Object.class);
            return objectMapper.convertValue(responseData.getBody(), Map.class).get("id").toString();
        } catch (Exception exception) {
            throw new IllegalStateException();
        }
    }
}
