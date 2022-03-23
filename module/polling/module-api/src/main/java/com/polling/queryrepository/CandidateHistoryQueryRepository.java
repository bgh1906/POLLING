package com.polling.queryrepository;

import com.polling.candidate.dto.response.FindPollHistoryResponseDto;

import java.util.List;

public interface CandidateHistoryQueryRepository {
    List<FindPollHistoryResponseDto> findVoteHistoryByCandidateId(Long id);
    List<FindPollHistoryResponseDto> findVoteHistoryByCandidateIdLimit50(Long id);
}