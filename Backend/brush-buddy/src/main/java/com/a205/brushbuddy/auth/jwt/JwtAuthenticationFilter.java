package com.a205.brushbuddy.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.*;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

//Spring security에 등록할 jwt 인증 필터
//jwt를 인증하기 위한 코드를 여기에 작성한다.
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_HEADER = "Bearer ";
    private static final String BLANK = "";

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            val token = getAccessTokenFromRequest(request);
            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token) == JwtValidationType.VALID_JWT) { // null 이 아니고 유효하다면
                val authentication = new UserAuthentication(getUserId(token), null, null); // 사용자의 식별자를 추출하지
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    // 요청으로 부터 액세스 토큰을 추출하는 메서드
    private String getAccessTokenFromRequest(HttpServletRequest request) {
        return isContainsAccessToken(request) ? getAuthorizationAccessToken(request) : null;
    }

    // 액세스 토큰이 있는지 확인하는 메서드
    private boolean isContainsAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION);
        return authorization != null && authorization.startsWith(BEARER_HEADER);
    }

    // 액세스 토큰을 헤더로 부터 가져온다.
    private String getAuthorizationAccessToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION).replaceFirst(BEARER_HEADER, BLANK);
    }

    // 사용자 아이디
    private int getUserId(String token) {
        return jwtTokenProvider.getUserFromJwt(token);
    }
}