package com.a205.brushbuddy.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class HashtagPK implements Serializable {
    @Column(name = "hashtag_content", nullable = false, length = 50)
    private String hashtagContent;
    private Long boardId;
}
