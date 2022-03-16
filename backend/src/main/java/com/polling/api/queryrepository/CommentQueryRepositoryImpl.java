package com.polling.api.queryrepository;

import com.polling.api.controller.candidate.dto.CommentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.core.entity.comment.QComment.comment;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<CommentDto> findCommentByCandidateId(Long id) {
        return query
                .select((Projections.constructor(CommentDto.class,
                        comment.id,
                        comment.user.id,
                        comment.user.name,
                        comment.content)))
                .from(comment)
                .where(comment.candidate.id.eq(id))
                .fetch();
    }
}
