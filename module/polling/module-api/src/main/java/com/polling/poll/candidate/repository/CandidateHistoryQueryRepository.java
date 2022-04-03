package com.polling.poll.candidate.repository;

import com.polling.poll.dto.candidate.response.FindCandidateHistoryResponseDto;
import java.time.LocalDateTime;
import java.util.List;

public interface CandidateHistoryQueryRepository {

  List<FindCandidateHistoryResponseDto> findByCandidateId(Long candidateId, int offset,
      int limit);

  Boolean existsByMemberIdAndPollIdInToday(Long memberId, Long pollId, LocalDateTime today);
}