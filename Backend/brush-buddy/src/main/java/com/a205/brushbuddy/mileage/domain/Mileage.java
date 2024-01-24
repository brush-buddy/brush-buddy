package com.a205.brushbuddy.mileage.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "mileage")
public class Mileage {
    @Id
    @GeneratedValue
    @Column(name = "mileage_id", nullable = false)
    private int mileageId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "workplace_id", nullable = true)
    private int workplaceId;

    @Column(name = "mileage_timtestamp", nullable = true)
    private Date mileageTimtestamp;

    @Column(name = "mileage_before", nullable = true)
    private int mileageBefore;

    @Column(name = "mileage_after", nullable = true)
    private int mileageAfter;

    @Column(name = "mileage_amount", nullable = true)
    private int mileageAmount;

    @Column(name = "mileage_content", nullable = true)
    private String mileageContent;
}
