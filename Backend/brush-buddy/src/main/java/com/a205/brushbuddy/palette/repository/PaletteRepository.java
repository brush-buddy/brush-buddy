package com.a205.brushbuddy.palette.repository;


import com.a205.brushbuddy.palette.domain.Palette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaletteRepository extends JpaRepository<Palette, Long> {

}
