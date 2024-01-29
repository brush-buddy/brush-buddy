package com.a205.brushbuddy.draft.domain;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "draft_category")
public class DraftCategory {
    @EmbeddedId
    private DraftCategoryID draftCategoryID;
}
