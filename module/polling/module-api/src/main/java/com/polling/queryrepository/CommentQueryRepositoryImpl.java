package com.polling.queryrepository;

import com.polling.candidate.dto.CommentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.entity.candidate.QCandidate.candidate;
import static com.polling.entity.comment.QComment.comment;
import static com.polling.entity.member.QMember.member;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<CommentDto> findAllByCandidateId(Long candidateId) {
        return query
                .select((Projections.constructor(CommentDto.class,
                        comment.id,
                        member.id,
                        member.nickname,
                        comment.content)))
                .from(comment)
                .innerJoin(comment.member, member)
                .innerJoin(comment.candidate, candidate)
                .where(candidate.id.eq(candidateId))
                .orderBy(comment.createdDate.asc())
                .fetch();
    }
}
