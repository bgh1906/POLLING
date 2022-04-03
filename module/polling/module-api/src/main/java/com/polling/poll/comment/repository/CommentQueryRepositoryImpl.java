package com.polling.poll.comment.repository;

import com.polling.poll.comment.dto.response.FindCommentResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.polling.member.entity.QMember.member;
import static com.polling.poll.comment.entity.QComment.comment;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CommentQueryRepositoryImpl implements CommentQueryRepository {

  private final JPAQueryFactory query;

  @Override
  public List<FindCommentResponseDto> findAllByCandidateId(Long candidateId) {
    return query
        .select((Projections.constructor(FindCommentResponseDto.class,
            comment.id,
            member.id,
            member.nickname,
            comment.content)))
        .from(comment)
        .innerJoin(comment.member, member)
        .where(comment.candidate.id.eq(candidateId))
        .orderBy(comment.createdDate.asc())
        .fetch();
  }
}
