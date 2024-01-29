package com.a205.brushbuddy.draft.service;

import com.a205.brushbuddy.draft.domain.Draft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import com.a205.brushbuddy.draft.domain.DraftCategory;
import com.a205.brushbuddy.draft.domain.DraftCategoryID;
import com.a205.brushbuddy.draft.dto.request.ColorRequestDto;
import com.a205.brushbuddy.draft.dto.request.DraftCreateRequestDto;
import com.a205.brushbuddy.draft.dto.response.DraftCreateResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftDetailResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;
import com.a205.brushbuddy.draft.repository.CategoryRepository;
import com.a205.brushbuddy.draft.repository.Draft.DraftRepository;
import com.a205.brushbuddy.draft.domain.Category;
import com.a205.brushbuddy.draft.repository.DraftCategory.DraftCategoryRepository;
import com.a205.brushbuddy.palette.domain.Palette;
import com.a205.brushbuddy.palette.repository.PaletteRepository;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.user.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DraftServiceImpl implements DraftService{

    private final DraftRepository draftRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PaletteRepository paletteRepository;
    private final DraftCategoryRepository draftCategoryRepository;

    public Page<DraftListResponseDto> getDraftList(Pageable pageable) {
        try {

            return draftRepository.findAll(pageable).map(p->DraftListResponseDto.builder()
                    .draftId(p.getDraftId())
                    .draftThumbnail(p.getDraftThumbnail())
                    .draftTimestamp(p.getDraftTimestamp())
                    .draftDownload(p.getDraftDownload())
                    .draftBookmark(p.getDraftBookmark())
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public DraftDetailResponseDto getDraftDetail(Long draftId) {
          try {
              Draft draft = draftRepository.findByDraftId(draftId);
            return DraftDetailResponseDto.builder().draftId(draft.getDraftId())
                .draftPrice(draft.getDraftPrice())
                .draftColorCode(draft.getDraftColorCode())
                .draftThumbnail(draft.getDraftThumbnail())
                .draftFileLink(draft.getDraftFileLink())
                .draftIsAI(draft.getDraftIsAI())
                .draftIsPublic(draft.getDraftIsPublic())
                .draftIsDefault(draft.getDraftIsDefault())
                .draftIsDeleted(draft.getDraftIsDeleted())
                .draftDownload(draft.getDraftDownload())
                .draftBookmark(draft.getDraftBookmark())
                .draftPrompt(draft.getDraftPrompt())
                .draftTimestamp(draft.getDraftTimestamp())
                .userId(draft.getUser().getUserId())
                .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public DraftCreateResponseDto createDraft(int userId, DraftCreateRequestDto draftCreateDto) {
        List<Category> categoryList = categoryRepository.findByCategoryContentIn(draftCreateDto.getCategoryList());
        // Long draftId = draftRepository.InsertDraft(draftCreateDto, userId);
        System.out.println(draftCreateDto.getPalette().toString());
        User user = userRepository.findByUserId(userId);

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(ColorRequestDto color : draftCreateDto.getPalette()){
            sb.append("\"");
            sb.append(color.getColorId());
            sb.append("\" : \"");

            sb.append(color.getColorCode());
            sb.append("\",");
        }
        String js = sb.toString();
        if(draftCreateDto.getPalette().size() != 0){
            js = js.substring(0, js.length()-1);
        }
        js += "}";

        Draft draft = Draft.builder()
            .draftColorCode(js)
            .draftThumbnail(draftCreateDto.getImageFile())
            .draftFileLink(draftCreateDto.getDraftFIleLink())
            .draftIsAI(draftCreateDto.isDraftIsAI())
            .draftIsPublic(draftCreateDto.isDraftShare())
            .draftPrompt(draftCreateDto.getDraftPrompt())
            .draftBookmark(0)
            .draftDownload(0)
            .draftIsDefault(false)
            .draftIsDeleted(false)
            .draftPrice(0)
            .draftTimestamp(new Timestamp(System.currentTimeMillis()))
            .user(user).build();

        draftRepository.save(draft);


        Palette palette = Palette.builder()
            .paletteName(draftCreateDto.getPaletteTitle())
            .paletteColorCode(js)
            .paletteCreatedAt(new Timestamp(System.currentTimeMillis()))
            .paletteLastModifiedTime(new Timestamp(System.currentTimeMillis()))
            .draft(draft).build();
        paletteRepository.save(palette);




        for(Category category : categoryList){
            draftCategoryRepository.insertDraftCategory(draft.getDraftId(), category.getCategoryId());
        }


        return new DraftCreateResponseDto(draft.getDraftId(), palette.getPaletteId());
    }
}
