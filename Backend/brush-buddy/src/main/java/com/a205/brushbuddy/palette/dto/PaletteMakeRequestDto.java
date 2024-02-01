package com.a205.brushbuddy.palette.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaletteMakeRequestDto {
    private Long draftId;
    private String paletteName;
}
