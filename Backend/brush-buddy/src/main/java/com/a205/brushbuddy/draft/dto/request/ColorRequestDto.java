package com.a205.brushbuddy.draft.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ColorRequestDto {
	int colorId;
	String colorCode;
}
