package com.a205.brushbuddy.mileage.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MileageDto {
    private Long userId;
    private Integer workplaceId;
    private Timestamp mileageTimestamp;
    private int mileageBefore;
    private int mileageAfter;
    private int mileageAmount;
    private String mileageContent;
}