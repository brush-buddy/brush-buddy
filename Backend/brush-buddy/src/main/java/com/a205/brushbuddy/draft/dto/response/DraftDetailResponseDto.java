package com.a205.brushbuddy.draft.dto.response;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DraftDetailResponseDto {
	private Long draftId;
	private Integer userId;
	private Integer draftPrice;

	private Boolean isAuthor;
	private String draftColorCode;

	private String draftThumbnail;

	private String draftFileLink;

	private Boolean draftIsAI;

	private Boolean draftIsPublic;

	private Boolean draftIsDefault;

	private Boolean draftIsDeleted;

	private Integer draftDownload;

	private Integer draftBookmark;

	private String draftPrompt;

	private Timestamp draftTimestamp;
}
