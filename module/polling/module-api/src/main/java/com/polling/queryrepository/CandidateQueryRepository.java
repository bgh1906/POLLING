package com.polling.queryrepository;

import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.candidate.dto.response.FindCandidateThumbnailResponseDto;

import java.util.List;

public interface CandidateQueryRepository {
    List<FindCandidateThumbnailResponseDto> findAllByPollIdOrderByVotesTotal(Long pollId);
    List<FindCandidateThumbnailResponseDto> findAllThumbnailByPollId(Long pollId);
    List<FindCandidateResponseDto> findAllByPollId(Long pollId);
}
