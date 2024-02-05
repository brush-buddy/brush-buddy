package com.a205.brushbuddy.auth.controller;

import com.a205.brushbuddy.auth.dto.KakaoTokenDto;
import com.a205.brushbuddy.auth.dto.SignInResponse;
import com.a205.brushbuddy.auth.service.AuthService;
import com.a205.brushbuddy.auth.service.KakaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.security.Principal;

@Slf4j
//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private  final KakaoService kakaoService;
//    @PostMapping
//    public ResponseEntity<?> signIn(@RequestHeader("Authorization") String socialAccessToken) {
//        SignInResponse response = authService.signIn(socialAccessToken);
//        return ResponseEntity.ok(response);
//    }


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

        //프론트로 해당 데이터 전송
//        String urlTemplate = "http://localhost:5173/login?at={p1}";
//        String url = UriComponentsBuilder.fromHttpUrl(urlTemplate)
//                .buildAndExpand(response.accessToken())
//                .toUriString();
//
//        URI redirectUri  = new URI(url);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(redirectUri);
//        httpHeaders.set("Set-Cookie", response.refreshToken());

        String cookie = authService.createHttpOnlyCookie(response.refreshToken()); // refresh-token을 http-only 쿠키로 전송

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie)
                .body(response.accessToken());
//        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping("/logout")
    public ResponseEntity<?> signOut(Principal principal) {
        int userId = Integer.parseInt(principal.getName());
        authService.signOut(userId);
        return ResponseEntity.ok(null);
    }

    @ResponseBody
    @DeleteMapping
    public ResponseEntity<?> withdrawal(Principal principal) {
        int userId = Integer.parseInt(principal.getName());
        authService.withdraw(userId);
        return ResponseEntity.ok(null);
    }

    // refresh token으로 access token 재발급 하기
    @ResponseBody
    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(value = "refreshtoken") String refreshToken){
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }
}
