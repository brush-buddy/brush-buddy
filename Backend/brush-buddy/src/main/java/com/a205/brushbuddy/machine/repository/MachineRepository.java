package com.a205.brushbuddy.machine.repository;

import com.a205.brushbuddy.machine.domain.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
}
