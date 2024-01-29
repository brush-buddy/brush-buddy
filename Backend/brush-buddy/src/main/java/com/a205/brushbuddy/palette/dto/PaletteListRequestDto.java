package com.a205.brushbuddy.palette.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaletteListRequestDto {
    @Builder.Default
    String order = "paletteCreatedAt"; // 정렬기준

    @Builder.Default
    Integer pageNum = 1; // 페이지 넘버

    @Builder.Default
    Integer listNum = 20; // 페이지당 표출 횟수
}
