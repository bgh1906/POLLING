package com.polling.poll.candidate.repository;


import static com.polling.member.entity.QMember.member;
import static com.polling.poll.candidate.entity.QCandidate.candidate;
import static com.polling.poll.candidate.entity.QCandidateGallery.candidateGallery;
import static com.polling.poll.candidate.entity.QCandidateHistory.candidateHistory;

import com.polling.poll.candidate.dto.response.FindCandidateHistoryResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CandidateQueryRepositoryImpl implements CandidateQueryRepository {

  private final JPAQueryFactory query;

  @Override
  public List<FindCandidateHistoryResponseDto> findHistoryById(Long candidateId, int offset,
      int limit) {
    return query.select(Projections.constructor(FindCandidateHistoryResponseDto.class,
            candidateHistory.member.nickname,
            candidateHistory.voteCount,
            candidateHistory.transactionId))
        .from(candidateHistory)
        .where(candidateHistory.candidate.id.eq(candidateId))
        .offset(offset)
        .limit(limit)
        .orderBy(candidateHistory.createdDate.desc())
        .fetch();
  }

  @Override
  public Boolean existsByMemberIdAndPollIdInToday(Long memberId, Long pollId,
      LocalDateTime today) {
    return query
        .selectOne()
        .from(candidateHistory)
        .innerJoin(candidateHistory.member, member)
        .innerJoin(candidateHistory.candidate, candidate)
        .where(candidateHistory.member.id.eq(memberId),
            candidateHistory.candidate.poll.id.eq(pollId),
            candidateHistory.createdDate.after(today),
            candidateHistory.createdDate.before(today.plusDays(1)))
        .fetchFirst() != null;
  }

  @Override
  public void deleteGalleryById(Long id) {
    query.delete(candidateGallery)
        .where(candidateGallery.in(
            JPAExpressions
                .select(candidateGallery)
                .from(candidateGallery)
                .where(candidateGallery.candidate.id.eq(id))
        )).execute();
  }


}
