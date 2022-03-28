package com.polling.queryrepository;

import com.polling.entity.candidate.QCandidateGallery;
import com.polling.poll.dto.candidate.response.FindAnonymousCandidateResponseDto;
import com.polling.entity.candidate.Candidate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.polling.entity.candidate.QCandidate.candidate;
import static com.polling.entity.candidate.QCandidateGallery.*;
import static com.polling.entity.poll.QPoll.poll;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CandidateQueryRepositoryImpl implements CandidateQueryRepository{
    private final JPAQueryFactory query;

    @Override
    public List<FindAnonymousCandidateResponseDto> findAllSimpleByPollIdOrderByVotesTotal(Long pollId) {
        return query
                .select((Projections.constructor(FindAnonymousCandidateResponseDto.class,
                        candidate.id,
                        candidate.name,
                        candidate.thumbnail,
                        candidate.voteTotalCount)))
                .from(candidate)
                .where(candidate.poll.id.eq(pollId))
                .orderBy(candidate.voteTotalCount.desc())
                .fetch();
    }

    @Override
    public List<FindAnonymousCandidateResponseDto> findAllSimpleByPollId(Long pollId) {
        return query
                .select((Projections.constructor(FindAnonymousCandidateResponseDto.class,
                        candidate.id,
                        candidate.name,
                        candidate.thumbnail,
                        candidate.voteTotalCount)))
                .from(candidate)
                .where(candidate.poll.id.eq(pollId))
                .fetch();
    }

    @Override
    public List<Candidate> findAllByPollId(Long pollId) {
        return query
                .select(candidate)
                .from(candidate)
                .where(candidate.poll.id.eq(pollId))
                .fetch();
    }

    @Override
    public void deleteByPollId(Long pollId) {
        query.delete(candidate)
                .where(candidate.poll.id.eq(pollId))
                .execute();
    }

    @Override
    public void deleteGalleriesByCandidateId(Long candidateId) {
        query.delete(candidateGallery)
                .where(candidateGallery.candidate.id.eq(candidateId))
                .execute();
    }

}
