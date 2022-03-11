package com.ssafy.core.entity.candidate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCandidate is a Querydsl query type for Candidate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCandidate extends EntityPathBase<Candidate> {

    private static final long serialVersionUID = 1182920149L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCandidate candidate = new QCandidate("candidate");

    public final com.ssafy.core.entity.common.QBaseTimeEntity _super = new com.ssafy.core.entity.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> created_date = _super.created_date;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modified_date = _super.modified_date;

    public final StringPath name = createString("name");

    public final StringPath profilePath = createString("profilePath");

    public final com.ssafy.core.entity.vote.QVote vote;

    public final NumberPath<Long> voteTotal = createNumber("voteTotal", Long.class);

    public QCandidate(String variable) {
        this(Candidate.class, forVariable(variable), INITS);
    }

    public QCandidate(Path<? extends Candidate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCandidate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCandidate(PathMetadata metadata, PathInits inits) {
        this(Candidate.class, metadata, inits);
    }

    public QCandidate(Class<? extends Candidate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.vote = inits.isInitialized("vote") ? new com.ssafy.core.entity.vote.QVote(forProperty("vote")) : null;
    }

}

