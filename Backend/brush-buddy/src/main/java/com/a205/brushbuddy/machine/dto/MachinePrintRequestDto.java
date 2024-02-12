package com.a205.brushbuddy.machine.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachinePrintRequestDto {
    String id;
    String RGBCode; // Hexcode
}
