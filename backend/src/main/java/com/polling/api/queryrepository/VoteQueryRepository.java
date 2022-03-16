package com.polling.api.queryrepository;

import com.polling.api.controller.candidate.dto.response.FindCandidateResponseDto;
import com.polling.api.controller.vote.dto.VoteResponseDto;
import com.polling.core.entity.vote.status.VoteStatus;

import java.util.List;

public interface VoteQueryRepository {
    List<FindCandidateResponseDto> findCandidatesSortByVoteTotal(Long id);
    List<VoteResponseDto> findVoteByStatus(VoteStatus status);
    List<VoteResponseDto> findAll();
}