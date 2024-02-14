package com.a205.brushbuddy.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 593477083L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final StringPath boardContent = createString("boardContent");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final BooleanPath boardIsDeleted = createBoolean("boardIsDeleted");

    public final NumberPath<Integer> boardLikeNumber = createNumber("boardLikeNumber", Integer.class);

    public final StringPath boardThumbnail = createString("boardThumbnail");

    public final DateTimePath<java.sql.Timestamp> boardTimestamp = createDateTime("boardTimestamp", java.sql.Timestamp.class);

    public final StringPath boardTitle = createString("boardTitle");

    public final NumberPath<Integer> boardWatch = createNumber("boardWatch", Integer.class);

    public final com.a205.brushbuddy.draft.domain.QDraft draft;

    public final com.a205.brushbuddy.user.domain.QUser user;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.draft = inits.isInitialized("draft") ? new com.a205.brushbuddy.draft.domain.QDraft(forProperty("draft"), inits.get("draft")) : null;
        this.user = inits.isInitialized("user") ? new com.a205.brushbuddy.user.domain.QUser(forProperty("user")) : null;
    }

}

