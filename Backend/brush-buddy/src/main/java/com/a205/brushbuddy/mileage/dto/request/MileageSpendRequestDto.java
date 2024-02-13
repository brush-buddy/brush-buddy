package com.a205.brushbuddy.mileage.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MileageSpendRequestDto {
    private Integer workplaceId;
    private int mileageAmount;
    private String mileageContent;
}
