package com.polling.token.repository;

import com.polling.token.dto.response.FindTokenUsageHistoryResponseDto;
import java.util.List;

public interface TokenUsageHistoryQueryRepository {
  List<FindTokenUsageHistoryResponseDto> findSecretByMemberIdAndCandidateId(Long memberId, Long candidateId);
}
