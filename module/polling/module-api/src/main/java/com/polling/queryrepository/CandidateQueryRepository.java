package com.polling.queryrepository;

import com.polling.poll.dto.candidate.response.FindAnonymousCandidateResponseDto;
import com.polling.entity.candidate.Candidate;

import java.util.List;

public interface CandidateQueryRepository {
    List<FindAnonymousCandidateResponseDto> findAllByPollIdOrderByVotesTotal(Long pollId);
    List<FindAnonymousCandidateResponseDto> findAllThumbnailByPollId(Long pollId);
    List<Candidate> findAllByPollId(Long pollId);
}
