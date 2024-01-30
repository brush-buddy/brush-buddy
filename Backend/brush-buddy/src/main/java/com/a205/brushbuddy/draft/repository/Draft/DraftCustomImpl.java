package com.a205.brushbuddy.draft.repository.Draft;

import com.a205.brushbuddy.draft.domain.QDraft;
import com.a205.brushbuddy.draft.dto.request.DraftCreateRequestDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DraftCustomImpl implements DraftCustom{

	private final JPAQueryFactory query;

}
