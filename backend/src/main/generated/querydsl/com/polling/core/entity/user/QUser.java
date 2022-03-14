package com.polling.core.entity.user;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1108003514L;

    public static final QUser user = new QUser("user");

    public final com.polling.core.entity.common.QBaseTimeEntity _super = new com.polling.core.entity.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_date = _super.created_date;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified_date = _super.modified_date;

    public final StringPath name = createString("name");

    public final NumberPath<Long> oauthId = createNumber("oauthId", Long.class);

    public final EnumPath<com.polling.core.entity.user.status.OAuthType> oauthType = createEnum("oauthType", com.polling.core.entity.user.status.OAuthType.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final EnumPath<com.polling.core.entity.user.status.UserRole> userRole = createEnum("userRole", com.polling.core.entity.user.status.UserRole.class);

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

