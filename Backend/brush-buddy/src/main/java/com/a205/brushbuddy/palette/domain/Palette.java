package com.a205.brushbuddy.palette.domain;

import com.a205.brushbuddy.draft.domain.Draft;
import com.a205.brushbuddy.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Data
@Entity
@Table(name = "palette")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Palette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "palette_id")
    private Long paletteId;

    @ManyToOne
    @JoinColumn(name="draft_id")
    private Draft draft;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "palette_name", nullable = false)
    private String paletteName;

    @Column(name = "palette_color_code", nullable = false, columnDefinition = "JSON")
    private String paletteColorCode;

    @UpdateTimestamp
    @Column(name = "palette_last_modified_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp paletteLastModifiedTime = Timestamp.valueOf(LocalDateTime.now());

    @CreationTimestamp
    @Column(name = "palette_created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp paletteCreatedAt = Timestamp.valueOf(LocalDateTime.now());

}