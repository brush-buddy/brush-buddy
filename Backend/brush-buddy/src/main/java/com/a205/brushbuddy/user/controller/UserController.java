package com.a205.brushbuddy.user.controller;

import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.user.dto.response.UserInfoResponseDto;
import com.a205.brushbuddy.user.service.UserService;
import com.a205.brushbuddy.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;
    public final JwtUtil jwtUtil;
    @GetMapping
    public ResponseEntity<?> getUser(HttpServletRequest request){
        Integer userid = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.UNAUTHORIZED));

        User user = userService.getUserInfo(userid);

        UserInfoResponseDto dto = UserInfoResponseDto.builder()
                .userNickName(user.getUserNickname())
                .userProfile(user.getUserProfile())
                .Mileage(user.getUserMileage())
                .build();

        return ResponseEntity.ok(dto);
    }
}
