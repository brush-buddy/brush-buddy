package com.a205.brushbuddy.machine.service;

import com.a205.brushbuddy.machine.domain.Machine;
import com.a205.brushbuddy.machine.domain.OwnerType;
import com.a205.brushbuddy.machine.dto.MachineRegisterRequestDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterResponseDto;
import com.a205.brushbuddy.machine.repository.MachineRepository;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.workplace.domain.Workplace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService{
    public final MachineRepository machineRepository;

    @Override
    public MachineRegisterResponseDto registerMachine(MachineRegisterRequestDto requestDto) {
        Machine machine = Machine.builder()
                .machineId(requestDto.getMachineId())
                .workplaceId(Workplace.builder().workplaceId(requestDto.getWorkplaceId()).build())
                .machinePaintAmount(0)
                .owner(OwnerType.valueOf(requestDto.getOwner()))
                .user(User.builder().userId(requestDto.getUserId()).build())
                .build();
        machineRepository.save(machine);
        return null;
    }

}
