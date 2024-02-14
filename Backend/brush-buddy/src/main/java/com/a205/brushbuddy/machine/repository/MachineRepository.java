package com.a205.brushbuddy.machine.repository;

import com.a205.brushbuddy.machine.domain.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    Optional<Machine> findByUser_UserId(Integer userId); // 사용중인 유저 아이디가 있는가
}
