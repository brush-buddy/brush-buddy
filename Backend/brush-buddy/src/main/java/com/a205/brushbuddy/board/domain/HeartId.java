package com.a205.brushbuddy.board.domain;

import java.io.Serializable;

import com.a205.brushbuddy.user.domain.User;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class HeartId implements Serializable {

	@ManyToOne // Heart(Many) : User(One)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;

	@ManyToOne // Heart(Many) : Board(One)
	@JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
	private Board board;

}
