package com.a205.brushbuddy.draft.domain;


import jakarta.persistence.EmbeddedId;

public class DraftCategory {
    @EmbeddedId
    private DraftCategoryID draftCategoryID;
}
