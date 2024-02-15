package com.a205.brushbuddy.palette.controller;

import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.palette.domain.Palette;
import com.a205.brushbuddy.palette.dto.*;
import com.a205.brushbuddy.palette.service.PaletteService;
import com.a205.brushbuddy.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/v1/palette")
@RequiredArgsConstructor
public class PaletteController {
    public final PaletteService paletteService;
    public final JwtUtil jwtUtil;

    @GetMapping("/allList")
    public ResponseEntity<?> getAllPaletteList(PaletteListRequestDto requestDto, HttpServletRequest request) throws Exception{
        //pageable 생성
        Pageable pageable = PageRequest.of(
            requestDto.getPageNum() - 1,
            requestDto.getListNum(),
            requestDto.getDirection(),
            requestDto.getOrder());

        List<Palette> result =  paletteService.getAllPaletteList(pageable);

        PaletteListResponseDto dto = PaletteListResponseDto.builder()
            .palettes(result.stream().map(m ->PaletteListResponseDto.PaletteDTO.builder()
                .nickName(m.getUser().getUserNickname())
                    .paletteName(m.getPaletteName())
                    .paletteId(m.getPaletteId())
                    .paletteCreatedAt(m.getPaletteCreatedAt().toString())
                    .paletteModifiedTime(m.getPaletteLastModifiedTime().toString()) // 최종 수정 일자
                    .paletteColorCode(m.getPaletteColorCode())
                    .draftImage(m.getDraft().getDraftThumbnail())
                    .build())
                .toList())
            .length(result.size())
            .pageNum(pageable.getPageNumber() + 1)
            .build();

        return  ResponseEntity.ok(dto);
    }


    @GetMapping("")
    public ResponseEntity<?> getPaletteList(PaletteListRequestDto requestDto, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송

        //pageable 생성
        Pageable pageable = PageRequest.of(
                requestDto.getPageNum() - 1,
                requestDto.getListNum(),
                requestDto.getDirection(),
                requestDto.getOrder());

        List<Palette> result =  paletteService.getPaletteList(userId, pageable);

        PaletteListResponseDto dto = PaletteListResponseDto.builder()
                .palettes(result.stream().map(m ->PaletteListResponseDto.PaletteDTO.builder()
                                .paletteName(m.getPaletteName())
                                .paletteId(m.getPaletteId())
                                .paletteCreatedAt(m.getPaletteCreatedAt().toString())
                                .paletteModifiedTime(m.getPaletteLastModifiedTime().toString()) // 최종 수정 일자
                                .paletteColorCode(m.getPaletteColorCode())
                                .draftImage(m.getDraft().getDraftThumbnail())
                                .build())
                        .toList())
                .length(result.size())
                .pageNum(pageable.getPageNumber() + 1)
                .build();

        return  ResponseEntity.ok(dto);
    }

    //팔레트 상세 조회
    @GetMapping("/{paletteId}")
    public ResponseEntity<?> getPaletteDetail(@PathVariable(value = "paletteId") Long paletteId, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        Palette result = paletteService.getPaletteDetail(userId, paletteId);

        PaletteDetailResponseDto dto = PaletteDetailResponseDto.builder()
                .paletteName(result.getPaletteName()) // 팔레트 이름
                .draftImage(result.getDraft().getDraftThumbnail()) // 워터마크 달린 사진
                .paletteColorCode(result.getPaletteColorCode()) // 팔레트 색깔 코드
                .paletteCreatedAt(result.getPaletteCreatedAt().toString()) // 팔레트 생성 일시
                .paletteModifiedTime(result.getPaletteLastModifiedTime().toString()) // 팔레트 수정 일시
                .isAdmin(result.getUser().getUserId().equals(userId) || result.getUser().isUserIsAdmin()) // TODO : admin 여부 확인
                .nickName(result.getUser().getUserNickname()) // 사용자 닉네임
                .build();
        return  ResponseEntity.ok(dto);
    }

    //팔레트 수정
    @PutMapping("/{paletteId}")
    public ResponseEntity<?> modifyPalette(@PathVariable(value = "paletteId") Long paletteId, @RequestBody PaletteModifyRequestDto requestDto, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송
        paletteService.modifyPalette(userId, paletteId, requestDto);
        return  ResponseEntity.ok().build();
    }

    // 팔레트 추가
    @PostMapping("")
    public ResponseEntity<?> newPalette(PaletteMakeRequestDto requestDto, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송

        Long id = paletteService.newPalette(userId, requestDto); // 팔레트 생성

        PaletteMakeResponseDto dto = PaletteMakeResponseDto.builder() // 팔레트 dto로 변경
                .paletteId(id)
                .build();

        return  ResponseEntity.ok(dto);
    }


    //팔레트 복제
    @PostMapping("/{paletteId}/duplicate")
    public ResponseEntity<?> duplicatePalette(@PathVariable(value = "paletteId") Long paletteId, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송

        Long id = paletteService.duplicatePalette(userId, paletteId);
        PaletteDuplicateResponseDto dto = PaletteDuplicateResponseDto.builder().paletteId(id).build();
        return  ResponseEntity.ok(dto);
    }

    //팔레트 삭제
    @DeleteMapping("/{paletteId}")
    public ResponseEntity<?> getPaletteList(@PathVariable(value = "paletteId") Long paletteId, HttpServletRequest request) throws Exception{
        Integer userId = jwtUtil.getUserId(request)
                .orElseThrow(() -> new BaseException(ErrorCode.INVALID_TOKEN)); // 헤더의 access token으로 userId 추출, null 반환시 유효하지 않은 토큰 오류 전송

        paletteService.deletePalette(userId,paletteId);
        return  ResponseEntity.ok().build();
    }
}
