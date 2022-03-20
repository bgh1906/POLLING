package com.polling.queryrepository;


import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.entity.vote.status.VoteStatus;
import com.polling.vote.dto.VoteResponseDto;

import java.util.List;

public interface VoteQueryRepository {
    List<FindCandidateResponseDto> findCandidatesSortByVoteTotal(Long id);
    List<VoteResponseDto> findVoteByStatus(VoteStatus status);
    List<VoteResponseDto> findAll();
}