package com.a205.brushbuddy.draft.service;

import com.a205.brushbuddy.draft.domain.Draft;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.a205.brushbuddy.draft.dto.request.DraftCreateRequestDto;
import com.a205.brushbuddy.draft.dto.response.DraftCreateResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftDetailResponseDto;
import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;

public interface DraftService{


	public Page<DraftListResponseDto> getDraftList(Pageable pageable);

	public DraftDetailResponseDto getDraftDetail(Long draftId);

	public DraftCreateResponseDto createDraft(int userId, DraftCreateRequestDto draftCreateDto);
}
