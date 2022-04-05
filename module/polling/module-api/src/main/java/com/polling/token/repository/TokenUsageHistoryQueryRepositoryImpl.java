package com.polling.token.repository;

import static com.polling.poll.candidate.entity.QCandidateGallery.candidateGallery;
import static com.polling.token.entity.QTokenUsageHistory.tokenUsageHistory;

import com.polling.token.dto.response.FindTokenUsageHistoryResponseDto;
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
public class TokenUsageHistoryQueryRepositoryImpl implements TokenUsageHistoryQueryRepository {

  private final JPAQueryFactory query;

  @Override
  public List<FindTokenUsageHistoryResponseDto> findSecretByMemberIdAndCandidateId(Long memberId,
      Long candidateId) {
    return query
        .select((Projections.constructor(FindTokenUsageHistoryResponseDto.class,
            candidateGallery.imagePath
        )))
        .from(candidateGallery)
        .where(candidateGallery.in(
            JPAExpressions
                .select(tokenUsageHistory.gallery)
                .from(tokenUsageHistory)
                .innerJoin(tokenUsageHistory.gallery, candidateGallery)
                .where(tokenUsageHistory.member.id.eq(memberId),
                    tokenUsageHistory.candidate.id.eq(candidateId))
        ))
        .fetch();
  }
}
