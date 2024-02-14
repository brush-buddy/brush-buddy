package com.a205.brushbuddy.machine.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMachine is a Querydsl query type for Machine
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMachine extends EntityPathBase<Machine> {

    private static final long serialVersionUID = -1834949445L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMachine machine = new QMachine("machine");

    public final NumberPath<Long> machineId = createNumber("machineId", Long.class);

    public final NumberPath<Integer> machinePaintAmount = createNumber("machinePaintAmount", Integer.class);

    public final EnumPath<OwnerType> owner = createEnum("owner", OwnerType.class);

    public final com.a205.brushbuddy.user.domain.QUser user;

    public final com.a205.brushbuddy.workplace.domain.QWorkplace workplaceId;

    public QMachine(String variable) {
        this(Machine.class, forVariable(variable), INITS);
    }

    public QMachine(Path<? extends Machine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMachine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMachine(PathMetadata metadata, PathInits inits) {
        this(Machine.class, metadata, inits);
    }

    public QMachine(Class<? extends Machine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.a205.brushbuddy.user.domain.QUser(forProperty("user")) : null;
        this.workplaceId = inits.isInitialized("workplaceId") ? new com.a205.brushbuddy.workplace.domain.QWorkplace(forProperty("workplaceId")) : null;
    }

}

