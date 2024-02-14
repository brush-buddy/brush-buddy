package com.a205.brushbuddy.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashtag is a Querydsl query type for Hashtag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashtag extends EntityPathBase<Hashtag> {

    private static final long serialVersionUID = -253582975L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHashtag hashtag = new QHashtag("hashtag");

    public final NumberPath<Integer> hashtagSerialNumber = createNumber("hashtagSerialNumber", Integer.class);

    public final QHashtagPK id;

    public QHashtag(String variable) {
        this(Hashtag.class, forVariable(variable), INITS);
    }

    public QHashtag(Path<? extends Hashtag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHashtag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHashtag(PathMetadata metadata, PathInits inits) {
        this(Hashtag.class, metadata, inits);
    }

    public QHashtag(Class<? extends Hashtag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QHashtagPK(forProperty("id"), inits.get("id")) : null;
    }

}

