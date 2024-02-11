package com.a205.brushbuddy.machine.dto;


import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineRegisterRequestDto {

    private Long machineId;
    private Long workplaceId;

    @Nullable
    private Integer userId;

    private String owner;

    @Nullable
    private Integer machinePaintAmount;
}
