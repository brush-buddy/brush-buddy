package com.a205.brushbuddy.mileage.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;

import com.a205.brushbuddy.mileage.domain.Mileage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MileageHistoryResponseDto {
    private List<Mileage> history;
    private Integer length;
    private Integer pageNum;
    private Integer totalPage;
}
