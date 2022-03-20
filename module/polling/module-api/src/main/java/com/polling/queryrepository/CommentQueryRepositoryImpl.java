package com.polling.queryrepository;

import com.polling.candidate.dto.CommentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.entity.comment.QComment.comment;
import static com.polling.entity.member.QMember.member;


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
                        member.id,
                        member.nickname,
                        comment.content)))
                .from(comment)
                .leftJoin(member, comment.member)
                .where(comment.candidate.id.eq(id))
                .fetch();
    }
}
