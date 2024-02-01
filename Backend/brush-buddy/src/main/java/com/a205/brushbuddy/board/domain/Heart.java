package com.a205.brushbuddy.board.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "heart")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Heart {
	@EmbeddedId
	private HeartId heartId;
}
