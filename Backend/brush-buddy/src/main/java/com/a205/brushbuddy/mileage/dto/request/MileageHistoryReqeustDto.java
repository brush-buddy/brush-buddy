package com.a205.brushbuddy.mileage.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MileageHistoryReqeustDto {
    @Builder.Default
    private Integer listNum = 10;
    @Builder.Default
    private Integer pageNum = 1;
}
