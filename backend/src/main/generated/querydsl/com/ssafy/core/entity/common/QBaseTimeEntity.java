package com.ssafy.core.entity.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseTimeEntity is a Querydsl query type for BaseTimeEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseTimeEntity extends EntityPathBase<BaseTimeEntity> {

    private static final long serialVersionUID = -1795460831L;

    public static final QBaseTimeEntity baseTimeEntity = new QBaseTimeEntity("baseTimeEntity");

    public final DateTimePath<java.time.LocalDateTime> created_date = createDateTime("created_date", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modified_date = createDateTime("modified_date", java.time.LocalDateTime.class);

    public QBaseTimeEntity(String variable) {
        super(BaseTimeEntity.class, forVariable(variable));
    }

    public QBaseTimeEntity(Path<? extends BaseTimeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseTimeEntity(PathMetadata metadata) {
        super(BaseTimeEntity.class, metadata);
    }

}

