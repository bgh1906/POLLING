package com.ssafy.api.queryrepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.controller.candidate.dto.response.FindCandidateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ssafy.core.entity.candidate.QCandidate.candidate;

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
}
