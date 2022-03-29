package com.polling.queryrepository;

import com.polling.entity.candidate.Candidate;
import com.polling.poll.dto.candidate.response.FindAnonymousCandidateResponseDto;
import java.util.List;

public interface CandidateQueryRepository {

  List<FindAnonymousCandidateResponseDto> findAllSimpleByPollId(Long pollId);

  List<Candidate> findAllByPollId(Long pollId);

  void deleteByPollId(Long pollId);

  void deleteGalleriesByCandidateId(Long candidateId);
}
