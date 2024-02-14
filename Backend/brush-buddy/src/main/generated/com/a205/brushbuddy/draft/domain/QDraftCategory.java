package com.a205.brushbuddy.draft.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDraftCategory is a Querydsl query type for DraftCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDraftCategory extends EntityPathBase<DraftCategory> {

    private static final long serialVersionUID = -84444199L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDraftCategory draftCategory = new QDraftCategory("draftCategory");

    public final QDraftCategoryID draftCategoryID;

    public QDraftCategory(String variable) {
        this(DraftCategory.class, forVariable(variable), INITS);
    }

    public QDraftCategory(Path<? extends DraftCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDraftCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDraftCategory(PathMetadata metadata, PathInits inits) {
        this(DraftCategory.class, metadata, inits);
    }

    public QDraftCategory(Class<? extends DraftCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.draftCategoryID = inits.isInitialized("draftCategoryID") ? new QDraftCategoryID(forProperty("draftCategoryID"), inits.get("draftCategoryID")) : null;
    }

}

