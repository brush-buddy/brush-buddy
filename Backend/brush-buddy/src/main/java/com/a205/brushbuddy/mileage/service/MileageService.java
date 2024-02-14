package com.a205.brushbuddy.mileage.service;

import com.a205.brushbuddy.mileage.domain.MileageLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.a205.brushbuddy.mileage.domain.Mileage;
import com.a205.brushbuddy.mileage.dto.request.MileageSpendRequestDto;

public interface MileageService {

    Page<Mileage> getMileageHistory(int userId, Pageable pageable);

    Mileage spendMileage(int userId, MileageSpendRequestDto MileageSpendRequestDto);

    Long requestOrderInfo(int price, int userId);

    void setMileageTid(Long mileageLogId, String tid);

    MileageLog getMileageLog(String tid);

    void addMileage(int userId, Long mileageLogId);
}
