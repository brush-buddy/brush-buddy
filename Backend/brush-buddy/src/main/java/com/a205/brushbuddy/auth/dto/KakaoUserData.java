package com.a205.brushbuddy.auth.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoUserData {
    String socialId;
    String email;
    String gender;
    String birthYear;
    String nickName;

    public void setUserData(Map<String,String> userData){ // Json 데이터를 매핑해주는 함수
        socialId = userData.get("id");
        email = userData.get("email");
        gender = userData.get("gender");
        birthYear = userData.get("birthyear");
        ObjectMapper om = new ObjectMapper();
        nickName = om.convertValue(userData.get("profile"), Map.class).get("nickname").toString();
    }
}
