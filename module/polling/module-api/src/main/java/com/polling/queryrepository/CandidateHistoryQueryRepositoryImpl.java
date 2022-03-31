package com.polling.queryrepository;

import static com.polling.entity.candidate.QCandidate.candidate;
import static com.polling.entity.candidate.QCandidateHistory.candidateHistory;
import static com.polling.entity.member.QMember.member;

import com.polling.poll.dto.candidate.response.FindCandidateHistoryResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CandidateHistoryQueryRepositoryImpl implements CandidateHistoryQueryRepository {

  private final JPAQueryFactory query;

  @Override
  public List<FindCandidateHistoryResponseDto> findByCandidateId(Long candidateId, int offset,
      int limit) {
    return query
        .select((Projections.constructor(FindCandidateHistoryResponseDto.class,
            member.nickname,
            candidateHistory.voteCount,
            candidateHistory.transactionId)))
        .from(candidateHistory)
        .innerJoin(candidateHistory.member, member)
        .where(candidateHistory.candidate.id.eq(candidateId))
        .orderBy(candidateHistory.createdDate.desc())
        .offset(offset)
        .limit(limit)
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

}
