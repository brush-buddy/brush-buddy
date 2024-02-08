package com.a205.brushbuddy.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoTokenDto{
    String token_type;
    String access_token;
    String id_token;
    Integer expires_in;
    String refresh_token;
    Integer refresh_token_expires_in;
    String scope;
}
