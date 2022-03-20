package com.polling.queryrepository;

import com.polling.candidate.dto.response.FindVoteHistoryResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.entity.candidate.QCandidateHistory.candidateHistory;
import static com.polling.entity.member.QMember.member;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CandidateHistoryQueryRepositoryImpl implements CandidateHistoryQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<FindVoteHistoryResponseDto> findVoteHistoryByCandidateId(Long id) {
        return query
                .select((Projections.constructor(FindVoteHistoryResponseDto.class,
                        member.nickname,
                        candidateHistory.voteCount,
                        candidateHistory.transactionId)))
                .from(candidateHistory)
                .leftJoin(member, candidateHistory.member)
                .where(candidateHistory.candidate.id.eq(id))
                .fetch();
    }

    @Override
    public List<FindVoteHistoryResponseDto> findVoteHistoryByCandidateIdLimit50(Long id) {
        return query
                .select((Projections.constructor(FindVoteHistoryResponseDto.class,
                        member.nickname,
                        candidateHistory.voteCount,
                        candidateHistory.transactionId)))
                .from(candidateHistory)
                .leftJoin(member, candidateHistory.member)
                .where(candidateHistory.candidate.id.eq(id))
                .limit(50)
                .fetch();
    }
}
