package com.develop_ping.union.reaction.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReaction is a Querydsl query type for Reaction
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReaction extends EntityPathBase<Reaction> {

    private static final long serialVersionUID = 981758531L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReaction reaction = new QReaction("reaction");

    public final com.develop_ping.union.common.base.QAuditingFields _super = new com.develop_ping.union.common.base.QAuditingFields(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> targetId = createNumber("targetId", Long.class);

    public final EnumPath<ReactionType> type = createEnum("type", ReactionType.class);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public final com.develop_ping.union.user.domain.entity.QUser user;

    public QReaction(String variable) {
        this(Reaction.class, forVariable(variable), INITS);
    }

    public QReaction(Path<? extends Reaction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReaction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReaction(PathMetadata metadata, PathInits inits) {
        this(Reaction.class, metadata, inits);
    }

    public QReaction(Class<? extends Reaction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.develop_ping.union.user.domain.entity.QUser(forProperty("user")) : null;
    }

}

