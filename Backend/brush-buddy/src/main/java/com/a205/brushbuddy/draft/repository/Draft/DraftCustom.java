package com.a205.brushbuddy.draft.repository.Draft;

import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.draft.dto.request.DraftCreateRequestDto;

public interface DraftCustom {
	public Long InsertDraft(DraftCreateRequestDto draftCreateDto, int userId);

}
