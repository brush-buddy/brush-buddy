package com.a205.brushbuddy.machine.service;

import com.a205.brushbuddy.machine.dto.MachinePrintRequestDto;
import com.a205.brushbuddy.machine.dto.MachinePrintResponseDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterRequestDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterResponseDto;

public interface MachineService {
    MachineRegisterResponseDto registerMachine(MachineRegisterRequestDto requestDto);

    MachinePrintResponseDto convertRGB2CMYKW(MachinePrintRequestDto requestDto);
}
