package com.a205.brushbuddy.palette.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaletteModifyRequestDto {
    private String paletteName;

    private Map<String, String> paletteColorCode;
}
