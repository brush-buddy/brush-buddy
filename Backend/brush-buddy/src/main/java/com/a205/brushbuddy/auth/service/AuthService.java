package com.a205.brushbuddy.auth.service;

import com.a205.brushbuddy.auth.dto.KakaoUserData;
import com.a205.brushbuddy.auth.dto.SignInResponse;
import com.a205.brushbuddy.auth.jwt.JwtTokenProvider;
import com.a205.brushbuddy.auth.jwt.UserAuthentication;
import com.a205.brushbuddy.auth.vo.Token;
import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.user.domain.Gender;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private static final int ACCESS_TOKEN_EXPIRATION = 1800000;
    private static final int REFRESH_TOKEN_EXPIRATION = 1209600000;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private  final KakaoService kakaoService;

    // 로그인
    @Transactional
    public SignInResponse signIn(String socialAccessToken) { // 액세스 토큰
        User user = getUser(socialAccessToken); // 액세스 토큰의  유저 찾기
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

    // refresh Token을 http-only 쿠키로 만드는 메소드
    public String createHttpOnlyCookie(String refreshToken){
        String cookieName = "refreshtoken";
        String cookieValue = refreshToken; // 쿠키벨류엔 글자제한이 이써, 벨류로 만들어담아준다.
        int maxAge = 60 * 60 * 24;
//        Cookie cookie = new Cookie(cookieName, cookieValue);

        ResponseCookie cookie = ResponseCookie.from(cookieName, cookieValue)
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .maxAge(maxAge)
                .build();

        return cookie.toString();
    }

    // refresh token으로 access token 재발급하는 메소드
    public String refresh(String refreshToken){
        Integer userId = jwtTokenProvider.getUserFromJwt(refreshToken);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.UNAUTHORIZED));
        String realRefreshToken = user.getUserRefreshtoken(); // 저장된 refreshToken 가지고 오기

        // refresh token이 같다면
        if(realRefreshToken.equals(refreshToken)){
            return createAccessToken(user);
        }
        else return null;
    }

    // refresh token으로 access token 생성하는 메소드
    private String createAccessToken(User user){
       return jwtTokenProvider.generateToken(new UserAuthentication(user.getUserId(),null,null), ACCESS_TOKEN_EXPIRATION);
    }


    // 유저 정보 가져오기
    private User getUser(String socialAccessToken) {
        KakaoUserData userData = getUserData(socialAccessToken);
//        String socialId = getSocialId(socialAccessToken);
        return signUp(userData);
    }
    private KakaoUserData getUserData(String socialAccessToken){
        KakaoUserData kakaoUserData = new KakaoUserData();
        kakaoUserData.setUserData(kakaoService.getKakaoData(socialAccessToken));
        return kakaoUserData;
    }

//    //액세스 토큰으로
//    private String getSocialId(String socialAccessToken) {
//          return getUserData(socialAccessToken).get("id");
//    }

    //회원 가입 - 이미 있다면 찾은거 반환
    private User signUp(KakaoUserData userData) {
        String socialId = userData.getSocialId();
        return userRepository.findBySocialId(socialId)
                .orElseGet(() -> saveUser(userData));
    }

    // 유저
    private User saveUser(KakaoUserData userData) {

        User user = User.builder()
                .userBirth(userData.getBirthYear())
                .userGender(Gender.valueOf(userData.getGender()))
                .socialId(userData.getSocialId())
                .userIsAdmin(false)
                .userMileage(0)
                .userIsWithdraw(false)
                .userNickname(userData.getNickName())
                .build();
        return userRepository.save(user);
    }

    // 사용자 정보를 통해  refreshToken을 User에 저장하고 jwt Token을 반환
    private Token getToken(User user) {
        Token token = generateToken(new UserAuthentication(user.getUserId(), null, null)); // jwt 토큰 생성
        user.updateRefreshToken(token.getRefreshToken()); // 우리 서버의 jwt 토큰을 저장
        return token; // 토큰 반환
    }

    // 토큰 객체 생성
    private Token generateToken(Authentication authentication) {
        return Token.builder()
                .accessToken(jwtTokenProvider.generateToken(authentication, ACCESS_TOKEN_EXPIRATION)) // 액세스 토큰 생성
                .refreshToken(jwtTokenProvider.generateToken(authentication, REFRESH_TOKEN_EXPIRATION)) // 리프레시 토큰 생성
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
