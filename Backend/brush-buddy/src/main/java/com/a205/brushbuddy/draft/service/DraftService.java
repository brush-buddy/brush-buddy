package com.a205.brushbuddy.draft.service;

import com.a205.brushbuddy.draft.domain.Draft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.a205.brushbuddy.draft.dto.response.DraftListResponseDto;
import com.a205.brushbuddy.draft.repository.DraftRepository;

import lombok.RequiredArgsConstructor;


public interface DraftService{


	public Page<DraftListResponseDto> getDraftList(Pageable pageable);

	public Draft getDraftDetail(int draftId);
}
