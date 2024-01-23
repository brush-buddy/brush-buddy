package com.a205.brushbuddy.draft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.a205.brushbuddy.draft.dto.request.DraftListRequestDto;
import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;
import com.a205.brushbuddy.draft.repository.DraftRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DraftService{

	@Autowired
	private DraftRepository draftRepository;


	public Page<DraftListResponseDto> getDraftList(DraftListRequestDto draftListRequestDto, Pageable pageable) {
		try {
			return draftRepository.selectAllDraft(pageable);
		} catch (Exception e) {
			return null;
		}
	}
}
