package com.a205.brushbuddy.mileage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.a205.brushbuddy.mileage.domain.Mileage;

import jakarta.transaction.Transactional;

@Repository
public interface MileageRepository extends JpaRepository<Mileage, Long> {

    @Transactional
    @Query
    Page<Mileage> findAllByUserOrderByMileageTimestampDesc(Integer userId, Pageable pageable);

}
