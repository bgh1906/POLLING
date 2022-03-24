package com.polling.queryrepository;

import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.entity.candidate.QCandidate.candidate;
import static com.polling.entity.poll.QPoll.poll;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CandidateQueryRepositoryImpl implements CandidateQueryRepository{
    private final JPAQueryFactory query;

    @Override
    public List<FindCandidateResponseDto> findAllByPollIdOrderByVotesTotal(Long pollId) {
        return query
                .select((Projections.constructor(FindCandidateResponseDto.class,
                        candidate.id,
                        candidate.name,
                        candidate.thumbnail,
                        candidate.voteTotalCount)))
                .from(candidate)
                .innerJoin(candidate.poll, poll)
                .where(poll.id.eq(pollId))
                .orderBy(candidate.voteTotalCount.desc())
                .fetch();
    }

    @Override
    public List<FindCandidateResponseDto> findAllByPollId(Long pollId) {
        return query
                .select((Projections.constructor(FindCandidateResponseDto.class,
                        candidate.id,
                        candidate.name,
                        candidate.thumbnail,
                        candidate.voteTotalCount)))
                .from(candidate)
                .innerJoin(candidate.poll, poll)
                .where(poll.id.eq(pollId))
                .fetch();
    }
}
