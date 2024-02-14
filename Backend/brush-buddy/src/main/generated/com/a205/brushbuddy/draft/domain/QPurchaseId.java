package com.a205.brushbuddy.draft.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchaseId is a Querydsl query type for PurchaseId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPurchaseId extends BeanPath<PurchaseId> {

    private static final long serialVersionUID = -283277790L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchaseId purchaseId = new QPurchaseId("purchaseId");

    public final QDraft draft;

    public final com.a205.brushbuddy.user.domain.QUser user;

    public QPurchaseId(String variable) {
        this(PurchaseId.class, forVariable(variable), INITS);
    }

    public QPurchaseId(Path<? extends PurchaseId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseId(PathMetadata metadata, PathInits inits) {
        this(PurchaseId.class, metadata, inits);
    }

    public QPurchaseId(Class<? extends PurchaseId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.draft = inits.isInitialized("draft") ? new QDraft(forProperty("draft"), inits.get("draft")) : null;
        this.user = inits.isInitialized("user") ? new com.a205.brushbuddy.user.domain.QUser(forProperty("user")) : null;
    }

}

