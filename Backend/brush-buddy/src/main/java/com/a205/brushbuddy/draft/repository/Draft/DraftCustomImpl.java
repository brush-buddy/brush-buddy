package com.a205.brushbuddy.draft.repository.Draft;

import com.a205.brushbuddy.draft.domain.QDraft;
import com.a205.brushbuddy.draft.dto.request.DraftCreateRequestDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DraftCustomImpl implements DraftCustom{

	private final JPAQueryFactory query;

	@Override
	public Long InsertDraft(DraftCreateRequestDto draftCreateDto, int userId) {
		QDraft draft = QDraft.draft;
		return null;
		// return query.insert(draft)
		// 	.columns(draft.user.userId
		// 		, draft.draftColorCode
		// 		, draft.draftThumbnail
		// 		, draft.draftFileLink
		// 		, draft.draftIsAI
		// 		, draft.draftIsPublic
		// 		, draft.draftPrompt)
		// 	.values(
		// 		userId,
		// 		draftCreateDto.getPalette(),
		// 		draftCreateDto.getImageFile(),
		// 		draftCreateDto.getDraftFIleLink(),
		// 		draftCreateDto.isDraftIsAI(),
		// 		draftCreateDto.isDraftShare(),
		// 		draftCreateDto.getDraftPrompt()
		//
		// 	)
		// 	.execute();
	}
}
