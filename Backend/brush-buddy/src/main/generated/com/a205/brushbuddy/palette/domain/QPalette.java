package com.a205.brushbuddy.palette.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPalette is a Querydsl query type for Palette
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPalette extends EntityPathBase<Palette> {

    private static final long serialVersionUID = -682379717L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPalette palette = new QPalette("palette");

    public final com.a205.brushbuddy.draft.domain.QDraft draft;

    public final StringPath paletteColorCode = createString("paletteColorCode");

    public final DateTimePath<java.sql.Timestamp> paletteCreatedAt = createDateTime("paletteCreatedAt", java.sql.Timestamp.class);

    public final NumberPath<Long> paletteId = createNumber("paletteId", Long.class);

    public final DateTimePath<java.sql.Timestamp> paletteLastModifiedTime = createDateTime("paletteLastModifiedTime", java.sql.Timestamp.class);

    public final StringPath paletteName = createString("paletteName");

    public final com.a205.brushbuddy.user.domain.QUser user;

    public QPalette(String variable) {
        this(Palette.class, forVariable(variable), INITS);
    }

    public QPalette(Path<? extends Palette> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPalette(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPalette(PathMetadata metadata, PathInits inits) {
        this(Palette.class, metadata, inits);
    }

    public QPalette(Class<? extends Palette> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.draft = inits.isInitialized("draft") ? new com.a205.brushbuddy.draft.domain.QDraft(forProperty("draft"), inits.get("draft")) : null;
        this.user = inits.isInitialized("user") ? new com.a205.brushbuddy.user.domain.QUser(forProperty("user")) : null;
    }

}

