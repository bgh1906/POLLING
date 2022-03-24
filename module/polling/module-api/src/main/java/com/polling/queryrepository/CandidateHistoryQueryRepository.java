package com.polling.queryrepository;

import com.polling.candidate.dto.response.FindPollHistoryResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CandidateHistoryQueryRepository {
    List<FindPollHistoryResponseDto> findByCandidateId(Long candidateId, int offset, int limit);
    Boolean existsByMemberIdAndPollIdInToday(Long memberId, Long pollId, LocalDateTime today);
}