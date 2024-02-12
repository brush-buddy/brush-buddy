package com.a205.brushbuddy.auth.config;


import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
@Getter
public class ValueConfig {

    @Value("${jwt.secret}")
    private String secretKey;

    //application.yml 에 저장된 secretkey를 가지고 와서 base64로 인코딩하는 메소드
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}