package com.a205.brushbuddy.machine.controller;


import com.a205.brushbuddy.machine.dto.MachineRegisterRequestDto;
import com.a205.brushbuddy.machine.dto.MachineRegisterResponseDto;
import com.a205.brushbuddy.machine.service.MachineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/machine")
@RequiredArgsConstructor
public class MachineController {
    private final MachineService machineService;

    // DB에 기기 정보를 저장한다.
    @PostMapping("/register")
    public ResponseEntity<?> registerMachine(@RequestBody MachineRegisterRequestDto requestDto){
        MachineRegisterResponseDto dto = machineService.registerMachine(requestDto);
        return ResponseEntity.ok(dto);
    }

    // 물감을 출력한다.

}
