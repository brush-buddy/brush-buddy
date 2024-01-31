package com.a205.brushbuddy.draft.repository.Draft;

import com.a205.brushbuddy.draft.domain.Draft;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class DraftCustomImpl implements DraftCustom{

	private final JPAQueryFactory query;

	@Override
	public Page<Draft> getDraftList(Pageable pageable, String search) {
		return null;
	}

	@Override
	public Page<Draft> getDraftList(Pageable pageable) {
		return null;
	}
}
