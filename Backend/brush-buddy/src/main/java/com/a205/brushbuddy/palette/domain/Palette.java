package com.a205.brushbuddy.palette.domain;

import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Table(name = "palette")
public class Palette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "palette_id")
    private Long paletteId;

    @ManyToOne
    @JoinColumn(name="draft_id")
    private Draft draftId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "palette_name", nullable = false)
    private String paletteName;

    @Column(name = "palette_color_code", nullable = false, columnDefinition = "JSON")
    private String paletteColorCode;

    @Column(name = "palette_last_modified_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp paletteLastModifiedTime;

    @Column(name = "palette_created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp paletteCreatedAt;
    @Getter
    @Id
    private Long id;

    // Getters and setters for all fields
}