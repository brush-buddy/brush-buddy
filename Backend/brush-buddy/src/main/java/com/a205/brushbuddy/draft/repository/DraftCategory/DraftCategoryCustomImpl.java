package com.a205.brushbuddy.draft.repository.DraftCategory;

import com.a205.brushbuddy.draft.domain.QDraftCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DraftCategoryCustomImpl implements DraftCategoryCustom {

	private final JPAQueryFactory query;


}
