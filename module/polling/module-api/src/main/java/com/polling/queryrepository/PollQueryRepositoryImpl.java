package com.polling.queryrepository;


import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.entity.candidate.QCandidate.candidate;
import static com.polling.entity.poll.QPoll.*;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class PollQueryRepositoryImpl implements PollQueryRepository {
    private final JPAQueryFactory query;

    @Override
    public List<FindCandidateResponseDto> findCandidatesSortByVoteTotal(Long id) {
        return query
                .select((Projections.constructor(FindCandidateResponseDto.class,
                        candidate.id,
                        candidate.name,
                        candidate.thumbnail,
                        candidate.voteTotalCount)))
                .from(candidate)
                .join(candidate.poll, poll)
                .where(candidate.poll.id.eq(id))
                .orderBy(candidate.voteTotalCount.desc())
                .fetch();
    }
}