package com.polling.queryrepository;

import com.polling.candidate.dto.response.FindPollHistoryResponseDto;

import java.util.List;

public interface CandidateHistoryQueryRepository {
    List<FindPollHistoryResponseDto> findByCandidateId(Long candidateId, int offset, int limit);
}