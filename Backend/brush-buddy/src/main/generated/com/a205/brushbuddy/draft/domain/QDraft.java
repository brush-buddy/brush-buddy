package com.a205.brushbuddy.draft.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDraft is a Querydsl query type for Draft
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDraft extends EntityPathBase<Draft> {

    private static final long serialVersionUID = -2043525701L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDraft draft = new QDraft("draft");

    public final NumberPath<Integer> draftBookmark = createNumber("draftBookmark", Integer.class);

    public final StringPath draftColorCode = createString("draftColorCode");

    public final NumberPath<Integer> draftDownload = createNumber("draftDownload", Integer.class);

    public final StringPath draftFileLink = createString("draftFileLink");

    public final NumberPath<Long> draftId = createNumber("draftId", Long.class);

    public final BooleanPath draftIsAI = createBoolean("draftIsAI");

    public final BooleanPath draftIsDefault = createBoolean("draftIsDefault");

    public final BooleanPath draftIsDeleted = createBoolean("draftIsDeleted");

    public final BooleanPath draftIsPublic = createBoolean("draftIsPublic");

    public final NumberPath<Integer> draftPrice = createNumber("draftPrice", Integer.class);

    public final StringPath draftPrompt = createString("draftPrompt");

    public final StringPath draftThumbnail = createString("draftThumbnail");

    public final DateTimePath<java.sql.Timestamp> draftTimestamp = createDateTime("draftTimestamp", java.sql.Timestamp.class);

    public final com.a205.brushbuddy.user.domain.QUser user;

    public QDraft(String variable) {
        this(Draft.class, forVariable(variable), INITS);
    }

    public QDraft(Path<? extends Draft> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDraft(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDraft(PathMetadata metadata, PathInits inits) {
        this(Draft.class, metadata, inits);
    }

    public QDraft(Class<? extends Draft> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.a205.brushbuddy.user.domain.QUser(forProperty("user")) : null;
    }

}

