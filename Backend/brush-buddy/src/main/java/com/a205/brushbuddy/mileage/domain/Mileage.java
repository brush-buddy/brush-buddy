package com.a205.brushbuddy.mileage.domain;

import com.a205.brushbuddy.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "mileage")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Mileage {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "mileage_id", nullable = false)
    private Long mileageId;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

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
