package com.a205.brushbuddy.board.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "heart")
public class Heart {
	@EmbeddedId
	private HeartId heartId;
}
