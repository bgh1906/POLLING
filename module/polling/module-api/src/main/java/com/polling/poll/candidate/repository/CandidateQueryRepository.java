package com.polling.poll.candidate.repository;


import com.polling.poll.candidate.dto.response.FindAnonymousCandidateResponseDto;
import com.polling.poll.candidate.entity.Candidate;
import java.util.List;

public interface CandidateQueryRepository {

  List<FindAnonymousCandidateResponseDto> findAllSimpleByPollId(Long pollId);

  List<Candidate> findAllByPollId(Long pollId);

  void deleteGalleryById(Long id);
}
