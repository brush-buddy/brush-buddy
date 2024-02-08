package com.a205.brushbuddy.util;

import com.a205.brushbuddy.auth.jwt.JwtTokenProvider;
import com.a205.brushbuddy.auth.jwt.JwtValidationType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private static final String BEARER_HEADER = "Bearer ";
    private static final String BLANK = "";

    private final JwtTokenProvider jwtTokenProvider;

    // 헤더로 부터 액세스토큰을 가지고 와서 UserId를 추출한다
    public Optional<Integer> getUserId(HttpServletRequest request){
        String authorization = request.getHeader(AUTHORIZATION); // 액세스 토큰 가지고 오기
        Integer userId = null;

        if(authorization != null && authorization.startsWith(BEARER_HEADER)){ // 토큰이 존재하지 않거나 Bearer 타입이 아니면 실패
            String jwtToken = authorization.replaceFirst(BEARER_HEADER, BLANK); // jwt access token 추출
            if(!jwtTokenProvider.validateToken(jwtToken).equals(JwtValidationType.VALID_JWT)){ // access token이 유효하지 않으면
                return Optional.of(null); // null 반환
            }
            userId = jwtTokenProvider.getUserFromJwt(jwtToken); // 토큰을 추출하여 UserId를 보내준다.
        }

        return Optional.of(userId);
    }
}
