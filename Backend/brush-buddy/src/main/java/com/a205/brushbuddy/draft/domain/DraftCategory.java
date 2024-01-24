package com.a205.brushbuddy.draft.domain;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "draft_category")
public class DraftCategory {
    @EmbeddedId
    private DraftCategoryID draftCategoryID;
}
