package com.polling.api.queryrepository;

import com.polling.api.controller.candidate.dto.response.FindCandidateResponseDto;

import java.util.List;

public interface VoteQueryRepository {
    List<FindCandidateResponseDto> findCandidatesSortByVoteTotal(Long id);
}