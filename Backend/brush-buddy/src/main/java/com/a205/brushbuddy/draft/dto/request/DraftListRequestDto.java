package com.a205.brushbuddy.draft.dto.request;

import org.springframework.data.domain.PageRequest;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
public class DraftListRequestDto {
	private String search;
	public DraftListRequestDto(String search) {
		this.search = search;
	}
}
