package com.develop_ping.union.auth.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOauthUser is a Querydsl query type for OauthUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOauthUser extends EntityPathBase<OauthUser> {

    private static final long serialVersionUID = -227409241L;

    public static final QOauthUser oauthUser = new QOauthUser("oauthUser");

    public final com.develop_ping.union.common.base.QAuditingFields _super = new com.develop_ping.union.common.base.QAuditingFields(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath oauthAccessToken = createString("oauthAccessToken");

    public final StringPath profileImage = createString("profileImage");

    public final StringPath provider = createString("provider");

    public final StringPath token = createString("token");

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public QOauthUser(String variable) {
        super(OauthUser.class, forVariable(variable));
    }

    public QOauthUser(Path<? extends OauthUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOauthUser(PathMetadata metadata) {
        super(OauthUser.class, metadata);
    }

}

