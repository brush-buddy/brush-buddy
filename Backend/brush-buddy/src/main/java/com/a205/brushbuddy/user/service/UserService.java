package com.a205.brushbuddy.user.service;

import java.util.HashMap;

public interface UserService {

    String getKakaoAccessToken(String code);

    HashMap<String, Object> getUserInfo(String accessToken);
}
