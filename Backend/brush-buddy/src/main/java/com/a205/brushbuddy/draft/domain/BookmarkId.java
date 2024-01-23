package com.a205.brushbuddy.draft.domain;

import com.a205.brushbuddy.user.domain.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class BookmarkId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "draft_id")
    private Draft draft;

}
