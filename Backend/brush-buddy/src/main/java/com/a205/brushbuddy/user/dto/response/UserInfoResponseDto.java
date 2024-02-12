package com.a205.brushbuddy.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserInfoResponseDto {
    String userNickName;
    String userProfile;
    Integer Mileage;
}
