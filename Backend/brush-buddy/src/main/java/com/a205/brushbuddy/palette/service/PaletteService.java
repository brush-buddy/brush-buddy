package com.a205.brushbuddy.palette.service;

import com.a205.brushbuddy.palette.domain.Palette;
import com.a205.brushbuddy.palette.dto.PaletteModifyRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaletteService {
    List<Palette> getPaletteList(String order, Pageable pageable); //팔레트 리스트 조사하기
    Palette getPaletteDetail(Long paletteId); // 팔레트 상세 조회

    boolean duplicatePalette(Long paletteId); // 팔레트 복제 성공시 true

    boolean modifyPalette(Long paletteId, PaletteModifyRequestDto requestDto); // 팔레트 수정

    boolean deletePalette(Long paletteId); // 팔레트 삭제
}
