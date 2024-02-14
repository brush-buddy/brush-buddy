package com.a205.brushbuddy.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHeartId is a Querydsl query type for HeartId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHeartId extends BeanPath<HeartId> {

    private static final long serialVersionUID = -155392586L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHeartId heartId = new QHeartId("heartId");

    public final QBoard board;

    public final com.a205.brushbuddy.user.domain.QUser user;

    public QHeartId(String variable) {
        this(HeartId.class, forVariable(variable), INITS);
    }

    public QHeartId(Path<? extends HeartId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHeartId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHeartId(PathMetadata metadata, PathInits inits) {
        this(HeartId.class, metadata, inits);
    }

    public QHeartId(Class<? extends HeartId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new com.a205.brushbuddy.user.domain.QUser(forProperty("user")) : null;
    }

}

