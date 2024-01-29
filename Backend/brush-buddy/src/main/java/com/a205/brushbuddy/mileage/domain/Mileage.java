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

	@Column(name = "mileage_timestamp", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
    private Timestamp mileageTimestamp;

    @Column(name = "mileage_before", nullable = true)
    private int mileageBefore;

    @Column(name = "mileage_after", nullable = true)
    private int mileageAfter;

    @Column(name = "mileage_amount", nullable = true)
    private int mileageAmount;

    @Column(name = "mileage_content", nullable = true, length = 100)
    private String mileageContent;
}
