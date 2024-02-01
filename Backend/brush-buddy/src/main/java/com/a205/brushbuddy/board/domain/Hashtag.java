package com.a205.brushbuddy.board.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hashtag")
public class Hashtag {

	@EmbeddedId
	private HashtagPK id;

	@Column(name = "hashtag_serial_number", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hashtagSerialNumber;

}
