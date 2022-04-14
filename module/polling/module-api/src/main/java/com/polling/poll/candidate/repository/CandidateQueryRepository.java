package com.polling.poll.candidate.repository;


import com.polling.poll.candidate.dto.response.FindCandidateHistoryResponseDto;
import java.time.LocalDateTime;
import java.util.List;

public interface CandidateQueryRepository {

  List<FindCandidateHistoryResponseDto> findHistoryById(Long candidateId, int offset,
      int limit);

  Boolean existsByMemberIdAndPollIdInToday(Long memberId, Long pollId, LocalDateTime today);

  void deleteGalleryById(Long id);
}
