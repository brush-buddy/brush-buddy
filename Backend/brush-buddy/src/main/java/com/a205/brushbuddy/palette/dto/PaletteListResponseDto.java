package com.a205.brushbuddy.palette.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

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
        private int paletteId;
        private String paletteName;
        private Map<String, String> paletteColorCode;
        private String paletteTimestamp;
    }
}
