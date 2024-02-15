package com.a205.brushbuddy.palette.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaletteDetailResponseDto {
    private String draftImage;
    private String paletteName;
    private String paletteColorCode;
    private String paletteModifiedTime;
    private String paletteCreatedAt;
    private Boolean isAdmin;
    private String nickName;
}
