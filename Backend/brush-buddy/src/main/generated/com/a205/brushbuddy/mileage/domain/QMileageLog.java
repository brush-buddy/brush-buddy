package com.a205.brushbuddy.mileage.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMileageLog is a Querydsl query type for MileageLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMileageLog extends EntityPathBase<MileageLog> {

    private static final long serialVersionUID = 464640745L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMileageLog mileageLog = new QMileageLog("mileageLog");

    public final NumberPath<Long> mileageLogId = createNumber("mileageLogId", Long.class);

    public final StringPath mileageLogStatus = createString("mileageLogStatus");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath tid = createString("tid");

    public final com.a205.brushbuddy.user.domain.QUser user;

    public QMileageLog(String variable) {
        this(MileageLog.class, forVariable(variable), INITS);
    }

    public QMileageLog(Path<? extends MileageLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMileageLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMileageLog(PathMetadata metadata, PathInits inits) {
        this(MileageLog.class, metadata, inits);
    }

    public QMileageLog(Class<? extends MileageLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.a205.brushbuddy.user.domain.QUser(forProperty("user")) : null;
    }

}

