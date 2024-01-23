package com.a205.brushbuddy.board.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hashtag")
public class Hashtag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hashtag_content", nullable = false, length = 50)
	private String hashtagContent;

	@ManyToOne // Hashtag(Many) : Board(One)
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;

	@Column(name = "hashtag_serial_number", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hashtagSerialNumber;

}
