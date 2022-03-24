package com.polling.queryrepository;

import com.polling.candidate.dto.response.FindCandidateResponseDto;

import java.util.List;

public interface CandidateQueryRepository {
    List<FindCandidateResponseDto> findAllByPollIdOrderByVotesTotal(Long pollId);
    List<FindCandidateResponseDto> findAllByPollId(Long pollId);
}
