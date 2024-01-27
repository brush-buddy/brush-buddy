package com.a205.brushbuddy.board.domain;

import java.sql.Timestamp;

import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
@Data
@Entity
@Table(name = "board")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id", nullable = false)
	private int BoardId;

	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	@ManyToOne // Board(Many) : User(One)
	private User user;

	@JoinColumn(name = "draft_id")
	@ManyToOne // Board(Many) : Draft(One)
	private Draft draft;

	@Column(name = "board_title", nullable = false, length = 200)
	private String boardTitle;

	@Column(name = "board_content", nullable = false, length = 1000)
	private String boardContent;

	@Column(name = "board_thumbnail", nullable = false, length = 255)
	private String boardThumbnail;

	@Column(name = "board_like_number", nullable = false)
	private int boardLikeNumber;

	@Column(name = "board_watch", nullable = false)
	private int boardWatch;

	@Column(name = "board_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp boardTimestamp;
}

