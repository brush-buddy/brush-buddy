package com.a205.brushbuddy.draft.service;

import com.a205.brushbuddy.board.domain.Board;
import com.a205.brushbuddy.board.dto.BoardListResponseDto;
import com.a205.brushbuddy.board.repository.BoardRepository;
import com.a205.brushbuddy.draft.domain.Category;
import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.draft.domain.DraftCategory;
import com.a205.brushbuddy.draft.dto.request.DraftCategoryModifyRequestDto;
import com.a205.brushbuddy.draft.dto.request.DraftCreateRequestDto;
import com.a205.brushbuddy.draft.dto.response.DraftCreateResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftDetailResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;
import com.a205.brushbuddy.draft.repository.BookmarkRepository;
import com.a205.brushbuddy.draft.repository.CategoryRepository;
import com.a205.brushbuddy.draft.repository.Draft.DraftRepository;
import com.a205.brushbuddy.draft.repository.DraftCategory.DraftCategoryRepository;
import com.a205.brushbuddy.draft.repository.PurchaseRepository;
import com.a205.brushbuddy.exception.BaseException;
import com.a205.brushbuddy.exception.ErrorCode;
import com.a205.brushbuddy.palette.domain.Palette;
import com.a205.brushbuddy.palette.repository.PaletteRepository;
import com.a205.brushbuddy.user.domain.User;
import com.a205.brushbuddy.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DraftServiceImpl implements DraftService{

    private final DraftRepository draftRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PaletteRepository paletteRepository;
    private final DraftCategoryRepository draftCategoryRepository;
    private final BookmarkRepository bookmarkRepository;
    private final PurchaseRepository purchaseRepository;
    private final BoardRepository boardRepository;
    public Page<DraftListResponseDto> getDraftList(Pageable pageable) {
        try {

            return draftRepository.findAllByDraftIsDeletedIsFalseAndDraftIsPublicIsTrue(pageable).map(p->DraftListResponseDto.builder()
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

    @Override
    public Page<DraftListResponseDto> getDraftListByCategory(Pageable pageable, String categoryContent) {
        Category category = categoryRepository.findByCategoryContent(categoryContent);
        if(category == null){
            return null;
        }
        List<Long> categoryIds = draftCategoryRepository.findDraftIdByCategoryId(category.getCategoryId());

        try {
            return draftRepository.findAllByDraftIdIn(categoryIds,pageable).map(p->DraftListResponseDto.builder()
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
        // return null;
    }

    public DraftDetailResponseDto getDraftDetail(int userId, Long draftId) throws Exception{


              Draft draft = draftRepository.findByDraftId(draftId);
              if(draft == null){
                  throw new BaseException(ErrorCode.NOT_FOUND_DATA);
              }
              if(!draft.getDraftIsPublic()){
                  if(draft.getUser().getUserId() != userId){
                      throw new BaseException(ErrorCode.UNAUTHORIZED);
                  }
              }
              List<Long> categoryIds = draftCategoryRepository.findCategoryIdByDraftId(draftId);
              List<Category> categories = categoryRepository.findCategoryContentByCategoryIdIn(categoryIds);
              List<String> categoryContents = categories.stream().map(Category::getCategoryContent).toList();
             boolean bookmarked = bookmarkRepository.findByBookmarkId_User_UserId_AndBookmarkId_Draft_DraftId(userId, draftId).orElseGet(()->null) != null;
             boolean buy = purchaseRepository.findAllByPurchaseId_Draft_DraftIdAndPurchaseId_User_UserId(draftId, userId).orElseGet(()->null) != null;
             return DraftDetailResponseDto.builder().draftId(draft.getDraftId())
                .draftPrice(draft.getDraftPrice())
                .draftColorCode(draft.getDraftColorCode())
                .draftThumbnail(draft.getDraftThumbnail())
                .draftIsAI(draft.getDraftIsAI())
                .draftIsPublic(draft.getDraftIsPublic())
                .draftIsDefault(draft.getDraftIsDefault())
                .draftIsDeleted(draft.getDraftIsDeleted())
                .draftDownload(draft.getDraftDownload())
                .draftBookmark(draft.getDraftBookmark())
                .draftPrompt(draft.getDraftPrompt())
                .draftTimestamp(draft.getDraftTimestamp())
                .userId(draft.getUser().getUserId())
                    .isBuy(buy)
                    .isBookmark(bookmarked)
                    .isAuthor(draft.getUser().getUserId() == userId)
                .categoryContents(categoryContents)
                .build();

    }

    @Transactional
    @Override
    public DraftCreateResponseDto createDraft(int userId, DraftCreateRequestDto draftCreateDto) throws
        JsonProcessingException {
        List<Category> categoryList = categoryRepository.findByCategoryContentIn(draftCreateDto.getCategoryList());
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_DATA));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(draftCreateDto.getPalette());

        Draft draft = Draft.builder()
            .draftColorCode(json)
            .draftThumbnail(draftCreateDto.getImageFile())
            .draftFileLink(draftCreateDto.getDraftFileLink())
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
            .user(user)
            .paletteColorCode(json)
            .paletteCreatedAt(new Timestamp(System.currentTimeMillis()))
            .paletteLastModifiedTime(new Timestamp(System.currentTimeMillis()))
            .draft(draft).build();
        paletteRepository.save(palette);

        for(Category category : categoryList){
            draftCategoryRepository.insertDraftCategory(draft.getDraftId(), category.getCategoryId());
        }

        return new DraftCreateResponseDto(draft.getDraftId(), palette.getPaletteId());
    }

    @Override
    public Page<Board> getBoardListByDraftId(Long draftId, Pageable pageable) {
        return boardRepository.findAllByDraft_DraftIdAndBoardIsDeletedIsFalse(draftId, pageable);
    }

    @Transactional
    @Override
    public void deleteDraft(int userId, Long draftId) {
        Draft draft = draftRepository.findByDraftId(draftId);
        if(draft.getUser().getUserId() == userId){
            draft.setDraftIsDeleted(true);
        }
        else{
            throw new BaseException(ErrorCode.UNAUTHORIZED);
        }
    }

    @Override
    @Transactional
    public boolean updateDraft(long draftId, DraftCategoryModifyRequestDto draftCategoryModifyRequestDto) {
        Draft draft = draftRepository.findByDraftId(draftId);
        if(draft == null){
            throw new BaseException(ErrorCode.NOT_FOUND_DATA);
        }

        if(draft.getDraftIsDeleted()){
            throw new BaseException(ErrorCode.NOT_FOUND_DATA);
        }

        List<Category> categoryList = categoryRepository.findByCategoryContentIn(draftCategoryModifyRequestDto.getCategoryList());
        draftCategoryRepository.deleteDraftCategory(draftId);
        for(Category category : categoryList){
            draftCategoryRepository.insertDraftCategory(draftId, category.getCategoryId());
        }
        return true;

    }

    // 북마크
    @Override
    @Transactional
    public void createBookmarkDraft(int userId, Long draftId) throws Exception{
        Draft draft = draftRepository.findByDraftId(draftId);
        if(draft.getDraftIsDeleted()){
            throw new BaseException(ErrorCode.NOT_FOUND_DATA);
        }

        bookmarkRepository.insertBookmark(userId, draftId);
        draft.setDraftBookmark(draft.getDraftBookmark() + 1);
    }

    @Override
    @Transactional
    public void deleteBookmarkDraft(int userId, Long draftId) {
        Draft draft = draftRepository.findByDraftId(draftId);

        if(draft.getDraftIsDeleted()){
            throw new BaseException(ErrorCode.NOT_FOUND_DATA);
        }
        bookmarkRepository.deleteBookmark(userId, draftId);
        draft.setDraftBookmark(draft.getDraftBookmark() - 1);
    }

    @Override
    @Transactional
    public void buyDraft(int userId, Long draftId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_DATA));
        Draft draft = draftRepository.findByDraftId(draftId);

        if(draft.getDraftIsDeleted()){
            throw new BaseException(ErrorCode.NOT_FOUND_DATA);
        }

        if(user.getUserMileage() < draft.getDraftPrice()){
            throw new BaseException(ErrorCode.NOT_ENOUGH_MILEAGE);
        }

        Palette palette = Palette.builder()
                .paletteName("구매한 도안의 팔레트")
                .user(user)
                .paletteColorCode(draft.getDraftColorCode())
                .paletteCreatedAt(new Timestamp(System.currentTimeMillis()))
                .paletteLastModifiedTime(new Timestamp(System.currentTimeMillis()))
                .draft(draft).build();
        paletteRepository.save(palette);

        user.setUserMileage(user.getUserMileage() - draft.getDraftPrice());
        purchaseRepository.insertPurchase(userId, draftId, draft.getDraftPrice());

        // purchaseRepository.save(null);

    }

    @Override
    public boolean bookmarkCheck(Integer userId, Long draftId) {
        return bookmarkRepository.findByBookmarkId_User_UserId_AndBookmarkId_Draft_DraftId(userId, draftId).orElseGet(()->null) == null;


    }

    @Override
    @Transactional
    public String downloadDraft(int userId, Long draftId) throws Exception{
        Draft draft = draftRepository.findByDraftId(draftId);
        if(draft == null){
            throw new BaseException(ErrorCode.NOT_FOUND_DATA);
        }
        if(draft.getDraftIsDeleted()){
            throw new BaseException(ErrorCode.NOT_FOUND_DATA);
        }
        // 작성한 사용자일 경우
        if(draft.getUser().getUserId() == userId){
            draft.setDraftDownload(draft.getDraftDownload() + 1);

            return draft.getDraftFileLink();
        }
        // 구매한 사용자일 경우
        if(purchaseRepository.findAllByPurchaseId_Draft_DraftIdAndPurchaseId_User_UserId(draftId, userId).orElseGet(()->null) != null){
            draft.setDraftDownload(draft.getDraftDownload() + 1);

            return draft.getDraftFileLink();
        }

        throw new BaseException(ErrorCode.UNAUTHORIZED);

    }


}
