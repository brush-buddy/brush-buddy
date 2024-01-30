package com.a205.brushbuddy.palette.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaletteModifyRequestDto {
    private Long paletteId;
    private String paletteName;
    private List<ColorDTO> paletteColorCode;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ColorDTO {
        private int colorId;
        private String colorValue;
    }
}
