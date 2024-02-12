package com.a205.brushbuddy.draft.dto.request;

import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DraftCreateRequestDto {
	private String ImageFile;
	private String draftFileLink;
	private String paletteTitle;



	// json
	private HashMap<Integer, String> palette;
	private boolean draftShare;
	private boolean draftIsAI;
	private String draftPrompt;

	private List<String> categoryList;
}
