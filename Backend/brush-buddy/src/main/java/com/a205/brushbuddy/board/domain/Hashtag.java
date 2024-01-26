package com.a205.brushbuddy.board.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hashtag")
public class Hashtag {

	@EmbeddedId
	private HashtagPK id;

	@MapsId("boardId")
	@ManyToOne(fetch = FetchType.LAZY) // Hashtag(Many) : Board(One)
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;

	@Column(name = "hashtag_serial_number", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hashtagSerialNumber;

}
