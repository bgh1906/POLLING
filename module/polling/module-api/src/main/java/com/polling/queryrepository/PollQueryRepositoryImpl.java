package com.polling.queryrepository;


import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.PollResponseDto;
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
public class PollQueryRepositoryImpl implements PollQueryRepository {
    private final JPAQueryFactory query;

    @Override
    public List<FindCandidateResponseDto> findCandidatesSortByVoteTotal(Long id) {
        return query
                .select((Projections.constructor(FindCandidateResponseDto.class,
                        candidate.id,
                        candidate.name,
                        candidate.candidateInfo,
                        candidate.voteTotal)))
                .from(candidate)
                .where(candidate.poll.id.eq(id))
                .orderBy(candidate.voteTotal.desc())
                .fetch();
    }

    @Override
    public List<PollResponseDto> findVoteByStatus(PollStatus status) {
        return query
                .select((Projections.constructor(PollResponseDto.class,
                        poll.id,
                        poll.title,
                        poll.createdDate,
                        poll.endDate
                        )))
                .from(poll)
                .where(poll.pollStatus.eq(status))
                .fetch();
    }

    @Override
    public List<PollResponseDto> findAll() {
        return query
                .select((Projections.constructor(PollResponseDto.class,
                        poll.id,
                        poll.title,
                        poll.createdDate,
                        poll.endDate
                )))
                .from(poll)
                .fetch();
    }
}