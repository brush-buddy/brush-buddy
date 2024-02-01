package com.a205.brushbuddy.board.domain;

import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
@Data
@Entity
@Table(name = "board")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id", nullable = false)
	private Long boardId;

	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY) // Board(Many) : User(One)
	private User user;

	@JoinColumn(name = "draft_id")
	@ManyToOne(fetch = FetchType.LAZY) // Board(Many) : Draft(One)
	private Draft draft;

	@Column(name = "board_title", nullable = false, length = 200)
	private String boardTitle;

	@Column(name = "board_content", nullable = false, length = 1000)
	private String boardContent;

	@Column(name = "board_thumbnail", nullable = false, length = 255)
	private String boardThumbnail;

	@Column(name = "board_like_number", nullable = false)
	private Integer boardLikeNumber;

	@Column(name = "board_watch", nullable = false)
	private Integer boardWatch;

	@ColumnDefault(value = "false")
	@Column(name = "board_is_deleted", nullable = false)
	private Boolean boardIsDeleted;

	@Column(name = "board_timestamp", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp boardTimestamp;
}

