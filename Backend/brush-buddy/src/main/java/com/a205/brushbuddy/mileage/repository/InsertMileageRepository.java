package com.a205.brushbuddy.mileage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.a205.brushbuddy.mileage.domain.Mileage;

import jakarta.transaction.Transactional;

public interface InsertMileageRepository extends JpaRepository<Mileage, Long> {

    @Transactional
    @Modifying
    @Query("INSERT INTO Mileage(user_id, workplace_id, mileage_before, mileage_after, mileage_amount, mileage_content) VALUES (:userId, :workplaceId, :mileageBefore, :mileageAfter, :mileageAmount, :mileageContent )")
    // 마일리지 사용 경우
    void insertChargedMileage(Long userId, Integer workplaceId, int mileageBefore, int mileageAfter, int mileageAmount, String mileageContent);
    @Transactional
    @Modifying
    @Query("INSERT INTO Mileage(user_id, workplace_id, mileage_before, mileage_after, mileage_amount, mileage_content) VALUES (:userId, :workplaceId, :mileageBefore, :mileageAfter, :mileageAmount, :mileageContent )")
    // 마일리지 충전 경우
    void insertSpentMileage(Long userId, Integer workplaceId, int mileageBefore, int mileageAfter, int mileageAmount, String mileageContent);
}
