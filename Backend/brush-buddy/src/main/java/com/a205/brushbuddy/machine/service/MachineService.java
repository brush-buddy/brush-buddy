package com.a205.brushbuddy.machine.service;

import com.a205.brushbuddy.machine.dto.MachinePrintRequestDto;
import com.a205.brushbuddy.machine.dto.MachinePrintResponseDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterRequestDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterResponseDto;

public interface MachineService {
    MachineRegisterResponseDto registerMachine(MachineRegisterRequestDto requestDto); // 기기 DB에 등록하기

    boolean connectMachine(Integer userId, Long machineId); // 기기 연결

    boolean disconnectMachine(Integer userId); // 기기 해제

    Long getLoginMahcineId(Integer userId);

    MachinePrintResponseDto convertRGB2CMYKW(Long machineId, MachinePrintRequestDto requestDto); // 색깔 변경 로직
}
