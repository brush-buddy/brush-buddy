package com.a205.brushbuddy.draft.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDraftCategoryID is a Querydsl query type for DraftCategoryID
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDraftCategoryID extends BeanPath<DraftCategoryID> {

    private static final long serialVersionUID = 453505716L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDraftCategoryID draftCategoryID = new QDraftCategoryID("draftCategoryID");

    public final QCategory category;

    public final QDraft draft;

    public QDraftCategoryID(String variable) {
        this(DraftCategoryID.class, forVariable(variable), INITS);
    }

    public QDraftCategoryID(Path<? extends DraftCategoryID> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDraftCategoryID(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDraftCategoryID(PathMetadata metadata, PathInits inits) {
        this(DraftCategoryID.class, metadata, inits);
    }

    public QDraftCategoryID(Class<? extends DraftCategoryID> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.draft = inits.isInitialized("draft") ? new QDraft(forProperty("draft"), inits.get("draft")) : null;
    }

}

