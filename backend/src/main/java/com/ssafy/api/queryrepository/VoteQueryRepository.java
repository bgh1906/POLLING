package com.ssafy.api.queryrepository;

import com.ssafy.api.controller.candidate.dto.response.FindCandidateResponseDto;

import java.util.List;

public interface VoteQueryRepository {
    List<FindCandidateResponseDto> findCandidatesSortByVoteTotal(Long id);
}
