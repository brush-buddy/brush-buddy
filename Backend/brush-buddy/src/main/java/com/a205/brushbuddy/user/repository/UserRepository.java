package com.a205.brushbuddy.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a205.brushbuddy.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //	User findByUserId(int userId);
    Optional<User> findBySocialId(String socialId); // 소셜 로그인

    User findUserByUserId(int userId);
}
