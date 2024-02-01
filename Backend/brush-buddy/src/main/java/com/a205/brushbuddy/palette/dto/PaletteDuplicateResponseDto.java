package com.a205.brushbuddy.palette.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaletteDuplicateResponseDto {
    Long paletteId;
}
