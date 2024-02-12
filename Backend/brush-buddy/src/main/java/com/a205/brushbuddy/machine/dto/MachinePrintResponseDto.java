package com.a205.brushbuddy.machine.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachinePrintResponseDto {
    String id;
    ColorDTO color;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ColorDTO{
        int c;
        int m;
        int y;
        int k;
        int w;
    }
}
