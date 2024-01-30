package com.a205.brushbuddy.draft.repository.Draft;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.draft.domain.QDraft;
import com.a205.brushbuddy.draft.dto.request.DraftCreateRequestDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DraftCustomImpl implements DraftCustom{

	private final JPAQueryFactory query;

	@Override
	public Page<Draft> getDraftList(Pageable pageable, String search) {
		return null;
	}
}
