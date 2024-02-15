package com.a205.brushbuddy.palette.repository;


import com.a205.brushbuddy.palette.domain.Palette;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaletteRepository extends JpaRepository<Palette, Long>{
    List<Palette> findAllByUser_UserId(Integer userId, Pageable pageable); // user 아이디로 가져오기
//    Optional<Palette> findByPaletteIdAndUser_UserId(Long paletteId, Integer UserId);


    Page<Palette> findAll(Pageable pageable);
}
