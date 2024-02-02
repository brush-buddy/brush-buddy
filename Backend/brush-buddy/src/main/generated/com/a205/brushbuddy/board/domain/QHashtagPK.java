package com.a205.brushbuddy.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashtagPK is a Querydsl query type for HashtagPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHashtagPK extends BeanPath<HashtagPK> {

    private static final long serialVersionUID = 1119899452L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHashtagPK hashtagPK = new QHashtagPK("hashtagPK");

    public final QBoard board;

    public final StringPath hashtagContent = createString("hashtagContent");

    public QHashtagPK(String variable) {
        this(HashtagPK.class, forVariable(variable), INITS);
    }

    public QHashtagPK(Path<? extends HashtagPK> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHashtagPK(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHashtagPK(PathMetadata metadata, PathInits inits) {
        this(HashtagPK.class, metadata, inits);
    }

    public QHashtagPK(Class<? extends HashtagPK> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

