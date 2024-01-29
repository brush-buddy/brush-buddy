package com.a205.brushbuddy.palette.controller;

import com.a205.brushbuddy.palette.dto.PaletteListRequestDto;
import com.a205.brushbuddy.palette.service.PaletteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/api/palette")
@RequiredArgsConstructor
public class PaletteController {
    public final PaletteService paletteService;

    //TODO :팔레트 리스트 조회
    @GetMapping("")
    public ResponseEntity<?> getPaletteList(PaletteListRequestDto requestDto){
        Pageable pageable = PageRequest.of(
                requestDto.getPageNum(),
                requestDto.getListNum());

        paletteService.getPaletteList(requestDto.getOrder(), pageable);
        return  ResponseEntity.ok().build();
    }
    //TODO : 팔레트 수정 메소드
    @PutMapping("/{paletteId}")
    public ResponseEntity<?> modifyPalette(@PathVariable(value = "palettedId") Long paletteId){
        return  ResponseEntity.ok().build();
    }

    //TODO : 팔레트 복제
    @PostMapping("/{paletteId}/duplicate")
    public ResponseEntity<?> duplicatePalette(@PathVariable(value = "palettedId") Long paletteId){
        return  ResponseEntity.ok().build();
    }

    //TODO : 팔레트 상세 조회
    @GetMapping("/{paletteId}")
    public ResponseEntity<?> getPaletteDetail(@PathVariable(value = "palettedId") Long paletteId){
        return  ResponseEntity.ok().build();
    }

    // TODO : 팔레트 삭제
    @DeleteMapping("/{paletteId}")
    public ResponseEntity<?> getPaletteList(@PathVariable(value = "palettedId") Long paletteId){
        return  ResponseEntity.ok().build();
    }
}
