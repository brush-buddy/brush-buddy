package com.a205.brushbuddy.palette.repository;


import com.a205.brushbuddy.palette.domain.Palette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PaletteRepository extends JpaRepository<Palette, Long> {
    List<Palette> findByUser_UserId(Integer userId, Pageable pageable);
}
