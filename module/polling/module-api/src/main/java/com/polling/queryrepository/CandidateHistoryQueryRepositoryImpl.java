package com.polling.queryrepository;

import com.polling.candidate.dto.response.FindPollHistoryResponseDto;
import com.polling.entity.poll.status.PollStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.entity.candidate.QCandidate.candidate;
import static com.polling.entity.candidate.QCandidateHistory.candidateHistory;
import static com.polling.entity.member.QMember.member;
import static com.polling.entity.poll.QPoll.poll;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CandidateHistoryQueryRepositoryImpl implements CandidateHistoryQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<FindPollHistoryResponseDto> findByCandidateId(Long candidateId, int offset, int limit) {
        return query
                .select((Projections.constructor(FindPollHistoryResponseDto.class,
                        member.nickname,
                        candidateHistory.voteCount,
                        candidateHistory.transactionId)))
                .from(candidateHistory)
                .innerJoin(candidateHistory.member, member)
                .innerJoin(candidateHistory.candidate, candidate)
                .where(candidateHistory.candidate.id.eq(candidateId))
                .orderBy(candidateHistory.createdDate.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}
