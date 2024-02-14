package com.a205.brushbuddy.mileage.repository;

import com.a205.brushbuddy.mileage.domain.MileageLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public  interface MileageLogRepository extends JpaRepository<MileageLog, Long> {
    Optional<MileageLog> findByMileageLogId(Long mileageLogId);

    Optional<MileageLog> findByTid(String tid);

}
