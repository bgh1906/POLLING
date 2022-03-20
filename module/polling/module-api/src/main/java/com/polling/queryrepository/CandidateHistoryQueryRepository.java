package com.polling.queryrepository;

import com.polling.candidate.dto.response.FindVoteHistoryResponseDto;

import java.util.List;

public interface CandidateHistoryQueryRepository {
    List<FindVoteHistoryResponseDto> findVoteHistoryByCandidateId(Long id);
    List<FindVoteHistoryResponseDto> findVoteHistoryByCandidateIdLimit50(Long id);
}