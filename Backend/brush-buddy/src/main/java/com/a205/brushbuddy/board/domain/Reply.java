package com.a205.brushbuddy.board.domain;

import com.a205.brushbuddy.user.domain.User;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "reply")
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY) // Reply(Many) : Board(One)
	@JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
	private Board board;

	@ManyToOne(fetch = FetchType.LAZY) // Reply(Many) : User(One)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;

	@Column(name = "reply_content", nullable = false, length = 100)
	private String replyContent;

	@Column(name = "reply_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp replyTimestamp;

	@Column(name = "reply_is_deleted", nullable = false)
	private boolean replyIsDeleted;

}
