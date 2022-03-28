package com.polling.queryrepository;

import com.polling.poll.dto.candidate.response.FindAnonymousCandidateResponseDto;
import com.polling.entity.candidate.Candidate;

import java.util.List;

public interface CandidateQueryRepository {
    List<FindAnonymousCandidateResponseDto> findAllSimpleByPollIdOrderByVotesTotal(Long pollId);
    List<FindAnonymousCandidateResponseDto> findAllSimpleByPollId(Long pollId);
    List<Candidate> findAllByPollId(Long pollId);
    void deleteByPollId(Long pollId);
    void deleteGalleriesByCandidateId(Long candidateId);
}
