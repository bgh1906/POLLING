package com.polling.queryrepository;

import static com.polling.entity.candidate.QCandidate.candidate;
import static com.polling.entity.candidate.QCandidateGallery.candidateGallery;

import com.polling.entity.candidate.Candidate;
import com.polling.poll.dto.candidate.response.FindAnonymousCandidateResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CandidateQueryRepositoryImpl implements CandidateQueryRepository {

  private final JPAQueryFactory query;

  @Override
  public List<FindAnonymousCandidateResponseDto> findAllSimpleByPollId(Long pollId) {
    return query
        .select((Projections.constructor(FindAnonymousCandidateResponseDto.class,
            candidate.id,
            candidate.contractIndex,
            candidate.name,
            candidate.thumbnail)))
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
  public void deleteGalleryById(Long id) {
    query.delete(candidateGallery)
        .where(candidateGallery.in(
            JPAExpressions
                .select(candidateGallery)
                .from(candidateGallery)
                .where(candidateGallery.candidate.id.eq(id))
        )).execute();
  }


}
