package com.a205.brushbuddy.palette.repository;

import com.a205.brushbuddy.palette.domain.Palette;
import com.a205.brushbuddy.palette.domain.QPalette;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaletteCustomImpl implements PaletteCustom{

	private final JPAQueryFactory query;

	@Override
	public Long InsertPalette(Long draftId, int userId, String paletteTitle, String paletteColorCode) {
		QPalette palette = QPalette.palette;
		return query.insert(palette)
			.columns(palette.draft.draftId
				, palette.user.userId
				, palette.paletteName
				, palette.paletteColorCode)
			.values(
				draftId,
				userId,
				paletteTitle,
				paletteColorCode
			)
			.execute();
	}
}
