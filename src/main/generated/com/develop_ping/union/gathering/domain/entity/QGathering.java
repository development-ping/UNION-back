package com.develop_ping.union.gathering.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGathering is a Querydsl query type for Gathering
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGathering extends EntityPathBase<Gathering> {

    private static final long serialVersionUID = 1209547699L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGathering gathering = new QGathering("gathering");

    public final com.develop_ping.union.common.base.QAuditingFields _super = new com.develop_ping.union.common.base.QAuditingFields(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> currentMember = createNumber("currentMember", Integer.class);

    public final DateTimePath<java.time.ZonedDateTime> gatheringDateTime = createDateTime("gatheringDateTime", java.time.ZonedDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxMember = createNumber("maxMember", Integer.class);

    public final ListPath<com.develop_ping.union.party.domain.entity.Party, com.develop_ping.union.party.domain.entity.QParty> parties = this.<com.develop_ping.union.party.domain.entity.Party, com.develop_ping.union.party.domain.entity.QParty>createList("parties", com.develop_ping.union.party.domain.entity.Party.class, com.develop_ping.union.party.domain.entity.QParty.class, PathInits.DIRECT2);

    public final QPlace place;

    public final BooleanPath recruited = createBoolean("recruited");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> views = createNumber("views", Long.class);

    public QGathering(String variable) {
        this(Gathering.class, forVariable(variable), INITS);
    }

    public QGathering(Path<? extends Gathering> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGathering(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGathering(PathMetadata metadata, PathInits inits) {
        this(Gathering.class, metadata, inits);
    }

    public QGathering(Class<? extends Gathering> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.place = inits.isInitialized("place") ? new QPlace(forProperty("place")) : null;
    }

}

