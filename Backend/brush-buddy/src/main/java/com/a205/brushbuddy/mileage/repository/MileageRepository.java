package com.a205.brushbuddy.mileage.repository;

import com.a205.brushbuddy.mileage.domain.Mileage;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MileageRepository extends JpaRepository<Mileage, Long> {
    Page<Mileage> findAllByUser_UserIdOrderByMileageTimestampDesc(Integer userId, Pageable pageable);
}
