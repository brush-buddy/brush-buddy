package com.a205.brushbuddy.draft.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class DraftMakeRequestDto {
    int userId;
    String prompt;
}
