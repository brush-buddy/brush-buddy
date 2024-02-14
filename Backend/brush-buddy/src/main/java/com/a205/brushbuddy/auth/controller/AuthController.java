package com.a205.brushbuddy.auth.controller;

import com.a205.brushbuddy.auth.dto.KakaoTokenDto;
import com.a205.brushbuddy.auth.dto.SignInResponse;
import com.a205.brushbuddy.auth.jwt.JwtTokenProvider;
import com.a205.brushbuddy.auth.jwt.JwtValidationType;
import com.a205.brushbuddy.auth.service.AuthService;
import com.a205.brushbuddy.auth.service.KakaoService;
import com.a205.brushbuddy.auth.vo.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private  final KakaoService kakaoService;
    private  final JwtTokenProvider jwtTokenProvider;
    @GetMapping
    public ResponseEntity<?> signInWithAuthCode(@RequestParam("code") String code) throws URISyntaxException {
        String tokens = kakaoService.getToken(code); // 카카오로 부터 access token, refresh token을 가지고 온다.
        String socialAccessToken; // 액세스 토큰
        KakaoTokenDto dto; // 카카오 관련 토큰

        log.info(tokens);

        ObjectMapper om = new ObjectMapper();
        try {
            dto = om.readValue(tokens, KakaoTokenDto.class); // 토큰을 dto로 변환
            socialAccessToken = dto.getToken_type() + " " + dto.getAccess_token(); // 카카오 액세스 토큰 제작
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 카카오 엑세스 토큰으로 로그인 진행 -> 우리 서버의 jwt로 만든다.
        SignInResponse response = authService.signIn(socialAccessToken);

        // refresh-token을 http-only 쿠키로 전송
        String cookie = authService.createHttpOnlyCookie("refreshToken", response.refreshToken());

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie)
                .body(response.accessToken()); // body에는 access token을 넣는다.
    }

    @PostMapping("/logout")
    public ResponseEntity<?> signOut(@Nullable Principal principal) {
        // 액세스 토큰이 있다면
        if(principal != null){
            int userId = Integer.parseInt(principal.getName());
            authService.signOut(userId); // DB내 저장된 리프레시 토큰 삭제
        }

        // 쿠키 만료 시키기
        String cookie = authService.setHttpOnlyCookieInvalidate("refreshToken");

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie)
                .body(null);
    }

    @DeleteMapping
    public ResponseEntity<?> withdrawal(Principal principal) {
        // 액세스 토큰이 있다면
        int userId = Integer.parseInt(principal.getName());
        authService.withdraw(userId);// DB내 저장된 리프레시 토큰 삭제

        // 쿠키 만료 시키기
        String cookie = authService.setHttpOnlyCookieInvalidate("refreshToken");

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie)
                .body(null);
    }

    // refresh token으로 access token 재발급 하기
    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(value = "refreshToken") String refreshToken){
        Token token = authService.refresh(refreshToken);
        // refresh-token을 http-only 쿠키로 전송
        String cookie = authService.createHttpOnlyCookie("refreshToken", token.getRefreshToken());

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie)
                .body(token.getAccessToken()); // body에는 access token을 넣는다.
    }

    @GetMapping("/isLogin")
    public ResponseEntity<?> isLogin(@CookieValue(value = "refreshToken") String refreshToken){
        if(jwtTokenProvider.validateToken(refreshToken) == JwtValidationType.VALID_JWT){
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
    }
}
