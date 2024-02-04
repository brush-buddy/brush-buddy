package com.a205.brushbuddy.auth.service;

import com.a205.brushbuddy.auth.dto.SignInResponse;
import com.a205.brushbuddy.auth.jwt.JwtTokenProvider;
import com.a205.brushbuddy.auth.jwt.UserAuthentication;
import com.a205.brushbuddy.auth.vo.Token;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private static final int ACCESS_TOKEN_EXPIRATION = 7200000;
    private static final int REFRESH_TOKEN_EXPIRATION = 1209600000;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private  final KakaoService kakaoService;

    // 로그인
    @Transactional
    public SignInResponse signIn(String socialAccessToken) { // 액세스 토큰
        User user = getUser(socialAccessToken);
        Token token = getToken(user);
        return SignInResponse.of(token);
    }

    // 로그아웃
    @Transactional
    public void signOut(int userId) {
        User user = findUser(userId);
        user.resetRefreshToken();
    }

    //회원 탈퇴
    @Transactional
    public void withdraw(int userId) {
        User user = findUser(userId);
        deleteUser(user);
    }

    // 유저 정보 가져오기
    private User getUser(String socialAccessToken) {
        String socialId = getSocialId(socialAccessToken).get("id");
        return signUp(socialId);
    }

    //액세스 토큰으로
    private Map<String,String> getSocialId(String socialAccessToken) {
          return kakaoService.getKakaoData(socialAccessToken);
    }

    //회원 가입 - 이미 있다면 찾은거 반환
    private User signUp(String socialId) {
        return userRepository.findBySocialId(socialId)
                .orElseGet(() -> saveUser(socialId));
    }

    // 유저
    private User saveUser(String socialId) {
        User user = User.builder()
                .socialId(socialId)
                .build();
        return userRepository.save(user);
    }

    // 사용자 정보를 통해 refreshToken을 User에 저장하고 Token을 가져옴
    private Token getToken(User user) {
        Token token = generateToken(new UserAuthentication(user.getUserId(), null, null));
        user.updateRefreshToken(token.getRefreshToken());
        return token;
    }

    // 토큰 객체 생성
    private Token generateToken(Authentication authentication) {
        return Token.builder()
                .accessToken(jwtTokenProvider.generateToken(authentication, ACCESS_TOKEN_EXPIRATION))
                .refreshToken(jwtTokenProvider.generateToken(authentication, REFRESH_TOKEN_EXPIRATION))
                .build();
    }

    // User 찾기
    private User findUser(int id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    // User 제거
    private void deleteUser(User user) {
        userRepository.delete(user);
    }
}
