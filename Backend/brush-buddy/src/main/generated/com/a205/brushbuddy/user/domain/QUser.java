package com.a205.brushbuddy.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 25045417L;

    public static final QUser user = new QUser("user");

    public final StringPath socialId = createString("socialId");

    public final StringPath userBirth = createString("userBirth");

    public final EnumPath<Gender> userGender = createEnum("userGender", Gender.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final BooleanPath userIsAdmin = createBoolean("userIsAdmin");

    public final BooleanPath userIsWithdraw = createBoolean("userIsWithdraw");

    public final NumberPath<Integer> userMileage = createNumber("userMileage", Integer.class);

    public final StringPath userNickname = createString("userNickname");

    public final StringPath userProfile = createString("userProfile");

    public final StringPath userRefreshtoken = createString("userRefreshtoken");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

