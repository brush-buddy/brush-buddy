package com.a205.brushbuddy.user.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public String getKakaoAccessToken(String code) {
        return null;
    }

    @Override
    public HashMap<String, Object> getUserInfo(String accessToken) {
        return null;
    }
}
