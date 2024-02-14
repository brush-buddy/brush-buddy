package com.a205.brushbuddy.mileage.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMileage is a Querydsl query type for Mileage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMileage extends EntityPathBase<Mileage> {

    private static final long serialVersionUID = 1745481307L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMileage mileage = new QMileage("mileage");

    public final NumberPath<Integer> mileageAfter = createNumber("mileageAfter", Integer.class);

    public final NumberPath<Integer> mileageAmount = createNumber("mileageAmount", Integer.class);

    public final NumberPath<Integer> mileageBefore = createNumber("mileageBefore", Integer.class);

    public final StringPath mileageContent = createString("mileageContent");

    public final NumberPath<Long> mileageId = createNumber("mileageId", Long.class);

    public final DateTimePath<java.sql.Timestamp> mileageTimestamp = createDateTime("mileageTimestamp", java.sql.Timestamp.class);

    public final com.a205.brushbuddy.user.domain.QUser user;

    public final NumberPath<Integer> workplaceId = createNumber("workplaceId", Integer.class);

    public QMileage(String variable) {
        this(Mileage.class, forVariable(variable), INITS);
    }

    public QMileage(Path<? extends Mileage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMileage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMileage(PathMetadata metadata, PathInits inits) {
        this(Mileage.class, metadata, inits);
    }

    public QMileage(Class<? extends Mileage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.a205.brushbuddy.user.domain.QUser(forProperty("user")) : null;
    }

}

