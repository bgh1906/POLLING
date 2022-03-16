package com.polling.api.queryrepository;

import com.polling.api.controller.candidate.dto.response.FindCandidateResponseDto;
import com.polling.api.controller.vote.dto.VoteResponseDto;
import com.polling.core.entity.vote.status.VoteStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.core.entity.candidate.QCandidate.candidate;
import static com.polling.core.entity.vote.QVote.vote;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class VoteQueryRepositoryImpl implements VoteQueryRepository{
    private final JPAQueryFactory query;

    @Override
    public List<FindCandidateResponseDto> findCandidatesSortByVoteTotal(Long id) {
        return query
                .select((Projections.constructor(FindCandidateResponseDto.class,
                        candidate.id,
                        candidate.name,
                        candidate.profilePath,
                        candidate.voteTotal)))
                .from(candidate)
                .where(candidate.vote.id.eq(id))
                .orderBy(candidate.voteTotal.desc())
                .fetch();
    }

    @Override
    public List<VoteResponseDto> findVoteByStatus(VoteStatus status) {
        return query
                .select((Projections.constructor(VoteResponseDto.class,
                        vote.id,
                        vote.name,
                        vote.createdDate,
                        vote.endDate
                        )))
                .from(vote)
                .where(vote.voteStatus.eq(status))
                .fetch();
    }

    @Override
    public List<VoteResponseDto> findAll() {
        return query
                .select((Projections.constructor(VoteResponseDto.class,
                        vote.id,
                        vote.name,
                        vote.createdDate,
                        vote.endDate
                )))
                .from(vote)
                .fetch();
    }
}