package com.a205.brushbuddy.palette.service;

import com.a205.brushbuddy.palette.domain.Palette;
import com.a205.brushbuddy.palette.dto.PaletteModifyRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaletteServiceImpl implements PaletteService{

    @Override
    public List<Palette> getPaletteList(Integer userId, String order, Pageable pageable) {
        return null;
    }

    @Override
    public Palette getPaletteDetail(Integer userId, Long paletteId) {
        return null;
    }

    @Override
    public boolean duplicatePalette(Integer userId, Long paletteId) {
        return false;
    }

    @Override
    public boolean modifyPalette(Integer userId, Long paletteId, PaletteModifyRequestDto requestDto) {
        return false;
    }

    @Override
    public boolean deletePalette(Integer userId, Long paletteId) {
        return false;
    }
}
