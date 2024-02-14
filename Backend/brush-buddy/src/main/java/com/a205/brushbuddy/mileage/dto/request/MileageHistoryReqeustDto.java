package com.a205.brushbuddy.mileage.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MileageHistoryReqeustDto {
    @Builder.Default
    private int listNum = 10;
    @Builder.Default
    private int pageNum = 1;
}
