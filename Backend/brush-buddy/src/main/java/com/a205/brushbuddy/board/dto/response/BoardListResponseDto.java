package com.a205.brushbuddy.board.dto.response;

import java.sql.Time;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class BoardListResponseDto {
	private int boardId;
	private String boardTitle;
	private String boardThumbnail;
	private int boardLikeNumber;
	private int boardWatch;
	private Timestamp boardTimestamp;

	public BoardListResponseDto(int boardId, String boardTitle, String boardThumbnail, int boardLikeNumber, int boardWatch, Timestamp boardTimestamp) {
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.boardThumbnail = boardThumbnail;
		this.boardLikeNumber = boardLikeNumber;
		this.boardWatch = boardWatch;
		this.boardTimestamp = boardTimestamp;
	}


}
