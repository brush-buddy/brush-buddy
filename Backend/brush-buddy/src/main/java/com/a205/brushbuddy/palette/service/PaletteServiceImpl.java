package com.a205.brushbuddy.palette.service;

import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.draft.repository.Draft.DraftRepository;
import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.palette.domain.Palette;
import com.a205.brushbuddy.palette.dto.PaletteMakeRequestDto;
import com.a205.brushbuddy.palette.dto.PaletteModifyRequestDto;
import com.a205.brushbuddy.palette.repository.PaletteRepository;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaletteServiceImpl implements PaletteService {
    private final PaletteRepository paletteRepository;
    private final DraftRepository draftRepository;
    private final UserRepository userRepository;
    @Override
    public List<Palette> getAllPaletteList(Pageable pageable) {
        return paletteRepository.findAll(pageable).getContent(); // 리스트 조
    }

    @Override
    public List<Palette> getPaletteList(Integer userId, Pageable pageable) {
        return paletteRepository.findAllByUser_UserId(userId, pageable); // 리스트 조회
    }

    @Override
    public Palette getPaletteDetail(Integer userId, Long paletteId) {
        // 팔레트 찾기
        Palette result = paletteRepository.findById(paletteId).orElseThrow(
                ()-> new BaseException(ErrorCode.NOT_FOUND_DATA)
        );

        // //사용자가 권한이 있는가
        // // TODO : Admin인지 확인하는 로직 필요
        // if(!result.getUser().getUserId().equals(userId)){ // UserId가 다르거나 admin이 아니면
        //     throw new BaseException(ErrorCode.NOT_PRIVIEGED);
        // }

        return result;
    }

    @Override
    public Long newPalette(Integer userId, PaletteMakeRequestDto requestDto) {
        //도안 정보 가져오기
        Draft draft = draftRepository.findByDraftId(requestDto.getDraftId());

        // TODO : 도안 구매 여부 확인
        // 사용자 본인이거나 admin이라면

        // 도안에서 기본 색깔 추출 해서
        // 팔레트에 대입하기
        Palette palette = Palette.builder()
                .paletteName(requestDto.getPaletteName()) // 팔레트 이름
                .user(User.builder().userId(userId).build()) // 생성하는 사람의 아이디
                .paletteColorCode(draft.getDraftColorCode()) // 기본 색깔 대입
                .draft(draft) // 연결 도안
                .build();

        //repository에 저장 후 id 반환
        Palette result = paletteRepository.save(palette);
        return result.getPaletteId();
    }

    // 팔레트 복제
    @Override
    public Long duplicatePalette(Integer userId, Long paletteId) {
        Palette originPalette = paletteRepository.findById(paletteId).orElseThrow(
                ()-> new BaseException(ErrorCode.NOT_FOUND_DATA)
        );

        //사용자가 권한이 있는가
//        // TODO : Admin인지 확인하는 로직 필요
//        if(!originPalette.getUser().getUserId().equals(userId)){ // UserId가 다르거나 admin이 아니면
//            throw new BaseException(ErrorCode.NOT_PRIVIEGED);
//        }
        if(userRepository.findUserByUserId(userId) == null){
            throw new BaseException(ErrorCode.NOT_FOUND_DATA);
        }
        //팔레트 복제
        Palette newPalette = Palette.builder()
                .paletteName(originPalette.getPaletteName()+"_copy")
                .paletteColorCode(originPalette.getPaletteColorCode())
                .user(userRepository.findUserByUserId(userId))
                .draft(originPalette.getDraft())
                .build();

        Palette result = paletteRepository.save(newPalette);
        return result.getPaletteId();
    }


    //팔레트 수정
    @Override
    public boolean modifyPalette(Integer userId, Long paletteId, PaletteModifyRequestDto requestDto) {
        // 팔레트 찾기
        Palette result = paletteRepository.findById(paletteId).orElseThrow(
                ()-> new BaseException(ErrorCode.NOT_FOUND_DATA)
        );

        //사용자가 권한이 있는가
        // TODO : Admin인지 확인하는 로직 필요
        if(!result.getUser().getUserId().equals(userId)){ // UserId가 다르거나 admin이 아니면
            throw new BaseException(ErrorCode.NOT_PRIVIEGED);
        }

        //팔레트 수정
        result.setPaletteName(requestDto.getPaletteName());
        ObjectMapper om = new ObjectMapper();
        try{
            String colorCodes =  om.writeValueAsString(requestDto.getPaletteColorCode()); // JSON -> String
            result.setPaletteColorCode(colorCodes); // 색깔 정보 불어와서 저장
        }catch (Exception e){
            throw new BaseException(ErrorCode.BAD_REQUEST);
        }

        // 팔레트 적용
        paletteRepository.save(result);
        return true;
    }

    //팔레트 삭제
    @Override
    public boolean deletePalette(Integer userId, Long paletteId){
        // 팔레트 찾기
        Palette result = paletteRepository.findById(paletteId).orElseThrow(
                ()-> new BaseException(ErrorCode.NOT_FOUND_DATA)
        );

        //사용자가 권한이 있는가
        // TODO : Admin인지 확인하는 로직 필요
        if(!result.getUser().getUserId().equals(userId)){ // UserId가 다르거나 admin이 아니면
            throw new BaseException(ErrorCode.NOT_PRIVIEGED);
        }

        //팔레트 삭제
        paletteRepository.delete(result);
        return true;
    }
}
