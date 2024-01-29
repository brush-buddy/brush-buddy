package com.a205.brushbuddy.palette.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a205.brushbuddy.palette.domain.Palette;

@Repository
public interface PaletteRepository extends JpaRepository<Palette, Long>, PaletteCustom{


}
