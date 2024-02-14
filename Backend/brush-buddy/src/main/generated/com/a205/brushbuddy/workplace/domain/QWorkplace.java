package com.a205.brushbuddy.workplace.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorkplace is a Querydsl query type for Workplace
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkplace extends EntityPathBase<Workplace> {

    private static final long serialVersionUID = 209771995L;

    public static final QWorkplace workplace = new QWorkplace("workplace");

    public final StringPath workplaceAddress = createString("workplaceAddress");

    public final NumberPath<Long> workplaceId = createNumber("workplaceId", Long.class);

    public final StringPath workplaceName = createString("workplaceName");

    public final NumberPath<Short> workplacePaintPrice = createNumber("workplacePaintPrice", Short.class);

    public final NumberPath<Long> workplaceRevenue = createNumber("workplaceRevenue", Long.class);

    public QWorkplace(String variable) {
        super(Workplace.class, forVariable(variable));
    }

    public QWorkplace(Path<? extends Workplace> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkplace(PathMetadata metadata) {
        super(Workplace.class, metadata);
    }

}

