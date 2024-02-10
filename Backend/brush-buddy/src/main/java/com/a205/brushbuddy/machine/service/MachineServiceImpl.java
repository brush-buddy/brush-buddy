package com.a205.brushbuddy.machine.service;

import com.a205.brushbuddy.machine.dto.MachineRegisterRequestDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterResponseDto;
import com.a205.brushbuddy.machine.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService{
    public final MachineRepository machineRepository;

    @Override
    public MachineRegisterResponseDto registerMachine(MachineRegisterRequestDto requestDto) {

        return null;
    }
}
