package com.a205.brushbuddy.mileage.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.a205.brushbuddy.mileage.domain.Mileage;
import com.a205.brushbuddy.mileage.dto.MileageDto;

import jakarta.transaction.Transactional;

@Repository
public interface SelectMileageRepository extends JpaRepository<Mileage, Long> {

    @Transactional
    @Query(value = "SELECT mileage FROM Mileage mileage WHERE user_id LIKE :userId AND mileage_timestamp BETWEEN :start AND :end ORDER BY mileage_timestamp DESC")
    List<MileageDto> findAllMileageBetweenStartAndEnd(Long userId, Timestamp start, Timestamp end);

    @Transactional
    @Query(value = "SELECT mileage FROM Mileage mileage WHERE user_id LIKE :userId AND mileage.mileage_amount > 0 AND mileage_timestamp BETWEEN :start AND :end ORDER BY mileage_timestamp DESC")
    List<MileageDto> findChargedMileageBetweenStartAndEnd(Long userId, Timestamp start, Timestamp end);

    @Transactional
    @Query(value = "SELECT mileage FROM Mileage mileage WHERE user_id LIKE :userId AND mileage.mileage_amount < 0 AND mileage_timestamp BETWEEN :start AND :end ORDER BY mileage_timestamp DESC")
    List<MileageDto> findSpentMileageBetweenStartAndEnd(Long userId, Timestamp start, Timestamp end);
}