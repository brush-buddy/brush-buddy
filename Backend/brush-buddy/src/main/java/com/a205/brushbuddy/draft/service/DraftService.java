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
			System.out.println("===================================="	);
			System.out.println(e.getMessage());
			return null;
		}
	}
}
