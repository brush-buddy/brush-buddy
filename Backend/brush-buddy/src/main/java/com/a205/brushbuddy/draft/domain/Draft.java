package com.a205.brushbuddy.draft.domain;

import com.a205.brushbuddy.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Slf4j
@Data
@Entity
@Table(name = "draft")
public class Draft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "draft_id")
    private Long draftId;

    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "draft_price", nullable = false)
    private Integer draftPrice;

    @Column(name = "draft_color_code", nullable = false, columnDefinition = "JSON")
    private String draftColorCode;

    @Column(name = "draft_thumbnail", nullable = false)
    private String draftThumbnail;

    @Column(name = "draft_file_link", nullable = false)
    private String draftFileLink;

    @Column(name = "draft_is_ai", nullable = false)
    private Boolean draftIsAI;

    @Column(name = "draft_is_public", nullable = false)
    private Boolean draftIsPublic;

    @Column(name = "draft_is_default", nullable = false)
    private Boolean draftIsDefault;

    @Column(name = "draft_is_deleted", nullable = false)
    private Boolean draftIsDeleted;

    @Column(name = "draft_download", nullable = false)
    private Integer draftDownload;

    @Column(name = "draft_bookmark", nullable = false)
    private Integer draftBookmark;

    @Column(name = "draft_prompt", length = 300)
    private String draftPrompt;

    @Column(name = "draft_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp draftTimestamp;

}