package com.polling.queryrepository;


import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.response.FindPollPageResponseDto;
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
                .innerJoin(candidate.poll, poll)
                .where(candidate.poll.id.eq(id))
                .orderBy(candidate.voteTotalCount.desc())
                .fetch();
    }

    @Override
    public List<FindPollPageResponseDto> findPollPage(int offset, int limit, PollStatus pollStatus) {
        return query
                .select(Projections.constructor(FindPollPageResponseDto.class,
                        poll.id,
                        poll.title,
                        poll.content,
                        poll.startDate,
                        poll.endDate))
                .from(poll)
                .where(poll.pollStatus.eq(pollStatus))
                .orderBy(poll.createdDate.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }
}