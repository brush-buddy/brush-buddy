package com.a205.brushbuddy.user.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "user_refreshtoken", nullable = false, length = 100)
    private String userRefreshtoken;

    @Column(name = "user_nickname", nullable = false, length = 50)
    private String userNickname;

    @Column(name = "user_birth", nullable = false, length = 4)
    private String userBirth;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('male','female')", name = "user_gender", nullable = false)
    private Gender userGender;

    @Column(name = "user_profile", length = 255)
    private String userProfile;

    @Column(name = "user_mileage", nullable = false)
    @ColumnDefault("0")
    private Integer userMileage;

    @Column(name = "user_is_admin", nullable = false)
    @ColumnDefault("false")
    private boolean userIsAdmin;

    @Column(name = "user_is_withdraw", nullable = false)
    @ColumnDefault("false")
    private boolean userIsWithdraw;

}
