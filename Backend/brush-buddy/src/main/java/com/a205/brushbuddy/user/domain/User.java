package com.a205.brushbuddy.user.domain;


import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "user_token", nullable = false, length = 100)
    private String userToken;

    @Column(name = "user_nickname", nullable = false, length = 50)
    private String userNickname;

    @Column(name = "user_birth", nullable = false, length = 4)
    private String userBirth;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('male','female')", name = "user_gender", nullable = false)
    private String userGender;

    @Column(name = "user_profile", length = 255)
    private String userProfile;

    @Column(name = "user_mileage", nullable = false)
    private int userMileage;

    @Column(name = "user_is_admin", nullable = false)
    private boolean userIsAdmin;

    @Column(name = "user_is_withdraw", nullable = false)
    private boolean userIsWithdraw;

}
