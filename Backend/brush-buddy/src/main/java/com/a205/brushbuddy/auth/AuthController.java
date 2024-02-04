package com.a205.brushbuddy.auth;

import com.a205.brushbuddy.auth.dto.SignInResponse;
import com.a205.brushbuddy.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> signIn(@RequestHeader("Authorization") String socialAccessToken) {
        SignInResponse response = authService.signIn(socialAccessToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> signOut(Principal principal) {
        long memberId = Long.parseLong(principal.getName());
        authService.signOut(memberId);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<?> withdrawal(Principal principal) {
        long memberId = Long.parseLong(principal.getName());
        authService.withdraw(memberId);
        return ResponseEntity.ok(null);
    }
}
