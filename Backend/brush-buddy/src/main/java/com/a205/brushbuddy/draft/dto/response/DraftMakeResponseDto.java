package com.a205.brushbuddy.draft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class DraftMakeResponseDto {
    private String image_url;
    private int left_cnt;
}
