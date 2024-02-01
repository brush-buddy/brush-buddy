package com.a205.brushbuddy.draft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class DraftCreateResponseDto {
	Long draftId;
	Long palettedId;
}
