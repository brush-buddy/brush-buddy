package com.a205.brushbuddy.board.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)  // Image(Many) : Board(One)
	@JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
	private Board board;
  
	@Column(name = "image_url", nullable = false, length = 255)
	private String imageUrl;

	@Column(name = "image_order", nullable = false)
	private int imageOrder;

}
