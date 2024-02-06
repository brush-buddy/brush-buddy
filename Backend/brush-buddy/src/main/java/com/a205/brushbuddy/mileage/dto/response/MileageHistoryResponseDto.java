package com.a205.brushbuddy.mileage.dto.response;

import java.util.List;

import com.a205.brushbuddy.mileage.dto.MileageDto;

import lombok.Data;

@Data
public class MileageHistoryResponseDto {
    List<MileageDto> history;
    int size;
}
