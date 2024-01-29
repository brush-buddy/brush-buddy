package com.a205.brushbuddy.draft.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DraftCreateRequestDto {
	private String ImageFile;
	private String draftFIleLink;
	private String paletteTitle;



	// json
	private List<ColorRequestDto> palette;
	private boolean draftShare;
	private boolean draftIsAI;
	private String draftPrompt;

	private List<String> categoryList;
}
