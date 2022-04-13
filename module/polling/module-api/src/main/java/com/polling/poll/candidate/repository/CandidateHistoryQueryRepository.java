package com.polling.poll.candidate.repository;

import com.polling.poll.candidate.dto.response.FindCandidateHistoryByMemberResponseDto;
import com.polling.poll.candidate.dto.response.FindCandidateHistoryResponseDto;
import java.time.LocalDateTime;
import java.util.List;

public interface CandidateHistoryQueryRepository {

  List<FindCandidateHistoryResponseDto> findByCandidateId(Long candidateId, int offset,
      int limit);

  List<FindCandidateHistoryByMemberResponseDto> findByCandidateByMemberId(Long memberId, int offset,
      int limit);

  List<FindCandidateHistoryResponseDto> findByCandidateByPollId(Long pollId, int offset,
      int limit);

  Boolean existsByMemberIdAndPollIdInToday(Long memberId, Long pollId, LocalDateTime today);
}