package com.a205.brushbuddy.board.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HashtagPK implements Serializable {
    @Column(name = "hashtag_content", nullable = false, length = 50)
    private String hashtagContent;

    @ManyToOne(fetch = FetchType.LAZY) // Hashtag(Many) : Board(One)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
}
