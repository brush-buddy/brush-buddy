package com.a205.brushbuddy.mileage.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MileageSpendRequestDto {
    private Long userId;
    private Integer workplaceId;
    private int mileageBefore;
    private int mileageAfter;
    private int mileageAmount;
    private String mileageContent;
}