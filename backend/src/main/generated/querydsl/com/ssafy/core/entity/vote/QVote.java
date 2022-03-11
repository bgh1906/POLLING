package com.ssafy.core.entity.vote;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QVote is a Querydsl query type for Vote
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVote extends EntityPathBase<Vote> {

    private static final long serialVersionUID = 278124267L;

    public static final QVote vote = new QVote("vote");

    public final com.ssafy.core.entity.common.QBaseTimeEntity _super = new com.ssafy.core.entity.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_date = _super.created_date;

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final StringPath host = createString("host");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified_date = _super.modified_date;

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final EnumPath<com.ssafy.core.entity.vote.status.VoteStatus> voteStatus = createEnum("voteStatus", com.ssafy.core.entity.vote.status.VoteStatus.class);

    public QVote(String variable) {
        super(Vote.class, forVariable(variable));
    }

    public QVote(Path<? extends Vote> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVote(PathMetadata metadata) {
        super(Vote.class, metadata);
    }

}

