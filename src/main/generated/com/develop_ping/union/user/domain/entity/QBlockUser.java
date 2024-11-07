package com.develop_ping.union.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlockUser is a Querydsl query type for BlockUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockUser extends EntityPathBase<BlockUser> {

    private static final long serialVersionUID = -213818688L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlockUser blockUser = new QBlockUser("blockUser");

    public final com.develop_ping.union.common.base.QAuditingFields _super = new com.develop_ping.union.common.base.QAuditingFields(this);

    public final QUser blockedUser;

    public final QUser blockingUser;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public QBlockUser(String variable) {
        this(BlockUser.class, forVariable(variable), INITS);
    }

    public QBlockUser(Path<? extends BlockUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlockUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlockUser(PathMetadata metadata, PathInits inits) {
        this(BlockUser.class, metadata, inits);
    }

    public QBlockUser(Class<? extends BlockUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockedUser = inits.isInitialized("blockedUser") ? new QUser(forProperty("blockedUser")) : null;
        this.blockingUser = inits.isInitialized("blockingUser") ? new QUser(forProperty("blockingUser")) : null;
    }

}

