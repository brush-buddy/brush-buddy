package com.a205.brushbuddy.draft.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.a205.brushbuddy.draft.dto.request.DraftCategoryModifyRequestDto;
import com.a205.brushbuddy.draft.dto.request.DraftCreateRequestDto;
import com.a205.brushbuddy.draft.dto.response.DraftCreateResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftDetailResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DraftService{


	Page<DraftListResponseDto> getDraftList(Pageable pageable);

	DraftDetailResponseDto getDraftDetail(Long draftId);

	DraftCreateResponseDto createDraft(int userId, DraftCreateRequestDto draftCreateDto) throws JsonProcessingException;

	void deleteDraft(int userId, Long draftId) ;

	boolean updateDraft(long draftId, DraftCategoryModifyRequestDto draftCategoryModifyRequestDto);


	// 북마크
	boolean createBookmarkDraft(int userId, Long draftId);
	// 북마크 제거
	boolean deleteBookmarkDraft(int userId, Long draftId);
}
