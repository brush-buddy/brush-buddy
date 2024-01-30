package com.a205.brushbuddy.draft.repository.DraftCategory;

import com.a205.brushbuddy.draft.domain.QDraftCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DraftCategoryCustomImpl implements DraftCategoryCustom {

	private final JPAQueryFactory query;

	@Override
	public boolean InsertCategory(Long draftId, Long CategoryId) {
		QDraftCategory draftCategory = QDraftCategory.draftCategory;
		query.insert(draftCategory)
			.columns(draftCategory.draftCategoryID.draft, draftCategory.draftCategoryID.category)
			.values(draftId, CategoryId);
		return true;

	}
}
