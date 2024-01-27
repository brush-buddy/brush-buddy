package com.a205.brushbuddy.draft.domain;

import com.a205.brushbuddy.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Slf4j
@Data
@Entity
@Table(name = "bookmark")
public class Bookmark {
    @EmbeddedId
    private BookmarkId bookmarkId;

    @Column(name = "bookmark_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp bookmarkTimestamp;

}

