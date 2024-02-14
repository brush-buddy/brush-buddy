package com.a205.brushbuddy.draft.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookmarkId is a Querydsl query type for BookmarkId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBookmarkId extends BeanPath<BookmarkId> {

    private static final long serialVersionUID = -1852510953L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookmarkId bookmarkId = new QBookmarkId("bookmarkId");

    public final QDraft draft;

    public final com.a205.brushbuddy.user.domain.QUser user;

    public QBookmarkId(String variable) {
        this(BookmarkId.class, forVariable(variable), INITS);
    }

    public QBookmarkId(Path<? extends BookmarkId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookmarkId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookmarkId(PathMetadata metadata, PathInits inits) {
        this(BookmarkId.class, metadata, inits);
    }

    public QBookmarkId(Class<? extends BookmarkId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.draft = inits.isInitialized("draft") ? new QDraft(forProperty("draft"), inits.get("draft")) : null;
        this.user = inits.isInitialized("user") ? new com.a205.brushbuddy.user.domain.QUser(forProperty("user")) : null;
    }

}

