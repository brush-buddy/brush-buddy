package com.a205.brushbuddy.mileage.domain;

import com.a205.brushbuddy.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mileage_log")
public class MileageLog {

    @Id
    @Column(name = "mileage_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mileageLogId;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;


    @Column(name = "mileage_log_status")
    private String mileageLogStatus;

    @Column(name="tid")
    private String tid;
}
