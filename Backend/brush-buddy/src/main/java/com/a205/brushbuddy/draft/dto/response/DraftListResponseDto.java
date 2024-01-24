package com.a205.brushbuddy.draft.dto.response;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class DraftListResponseDto {
	private Long draftId;
	private String draftThumbnail;
	private Timestamp draftTimestamp;
	private int draftDownload;
	private int draftBookmark;

	public DraftListResponseDto(Long draftId, String draftThumbnail, Timestamp draftTimestamp, int draftDownload, int draftBookmark) {
		this.draftId = draftId;
		this.draftThumbnail = draftThumbnail;
		this.draftTimestamp = draftTimestamp;
		this.draftDownload = draftDownload;
		this.draftBookmark = draftBookmark;
	}
}


