package com.a205.brushbuddy.palette.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaletteListResponseDto {
    private int pageNum;
    private int length;
    private List<PaletteDTO> palettes;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaletteDTO {
        private String draftImage;
        private Long paletteId;
        private String paletteName;
        private String paletteColorCode;
        private String paletteModifiedTime;
        private String paletteCreatedAt;
        private String nickName;
    }
}
