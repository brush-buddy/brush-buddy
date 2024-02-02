package com.a205.brushbuddy.draft.domain;

import com.a205.brushbuddy.user.domain.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;


@Embeddable
public class DraftCategoryID implements Serializable {

    @ManyToOne
    @JoinColumn(name = "draft_id")
    private Draft draft;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
