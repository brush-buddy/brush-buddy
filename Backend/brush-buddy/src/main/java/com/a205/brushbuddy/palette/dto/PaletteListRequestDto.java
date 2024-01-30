package com.a205.brushbuddy.palette.dto;

import lombok.*;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaletteListRequestDto {
    @Builder.Default
    String order = "paletteCreatedAt"; // 정렬기준

    @Builder.Default
    Sort.Direction direction = Sort.Direction.DESC; // 오름차순 or 내림차순

    @Builder.Default
    Integer pageNum = 1; // 페이지 넘버

    @Builder.Default
    Integer listNum = 20; // 페이지당 표출 횟수
}
