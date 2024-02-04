package com.a205.brushbuddy.auth.controller;

import com.a205.brushbuddy.auth.dto.KakaoTokenDto;
import com.a205.brushbuddy.auth.dto.SignInResponse;
import com.a205.brushbuddy.auth.service.AuthService;
import com.a205.brushbuddy.auth.service.KakaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
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
    public ResponseEntity<?> signInWithAuthCode(@RequestParam("code") String code){
        String tokens = kakaoService.getAccessToken(code);
        String socialAccessToken;

        System.out.println(tokens);

        ObjectMapper om = new ObjectMapper();
        try{
           KakaoTokenDto dto = om.readValue(tokens, KakaoTokenDto.class);
           socialAccessToken = dto.getToken_type()+ " " + dto.getAccess_token();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(socialAccessToken);
        SignInResponse response = authService.signIn(socialAccessToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> signOut(Principal principal) {
        int userId = Integer.parseInt(principal.getName());
        authService.signOut(userId);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<?> withdrawal(Principal principal) {
        int userId = Integer.parseInt(principal.getName());
        authService.signOut(userId);
        return ResponseEntity.ok(null);
    }
}
