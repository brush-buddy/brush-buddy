package com.a205.brushbuddy.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a205.brushbuddy.user.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	User findByUserId(int userId);
	Optional<User> findBySocialId(String socialId); // 소셜 로그인
}
