package com.a205.brushbuddy.palette.repository;

import com.a205.brushbuddy.palette.domain.Palette;

public interface PaletteCustom {
	Long InsertPalette(Long draftId, int userId, String paletteTitle, String palette);
}
