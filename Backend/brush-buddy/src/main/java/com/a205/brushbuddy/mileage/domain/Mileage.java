package com.a205.brushbuddy.mileage.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "mileage")
@NoArgsConstructor
public class Mileage {
    @Id
    @GeneratedValue
    @Column(name = "mileage_id", nullable = false)
    private Long mileageId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "workplace_id", nullable = true)
    private Integer workplaceId;

    @Column(name = "mileage_timestamp", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp mileageTimestamp;

    @Column(name = "mileage_before", nullable = false)
    private int mileageBefore;

    @Column(name = "mileage_after", nullable = false)
    private int mileageAfter;

    @Column(name = "mileage_amount", nullable = false)
    private int mileageAmount;

    @Column(name = "mileage_content", nullable = false, length = 100)
    private String mileageContent;
}
