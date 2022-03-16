package com.polling.api.queryrepository;

import com.polling.api.controller.candidate.dto.response.FindVoteHistoryResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.core.entity.candidate.QCandidateHistory.candidateHistory;
import static com.polling.core.entity.member.QMember.member;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CandidateHistoryQueryRepositoryImpl implements CandidateHistoryQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<FindVoteHistoryResponseDto> findVoteHistoryByCandidateId(Long id) {
        return query
                .select((Projections.constructor(FindVoteHistoryResponseDto.class,
                        candidateHistory.member.nickname,
                        candidateHistory.voteCount,
                        candidateHistory.transactionId)))
                .from(candidateHistory)
                .leftJoin(member, candidateHistory.member)
                .where(candidateHistory.candidate.id.eq(id))
                .fetch();
    }
}
