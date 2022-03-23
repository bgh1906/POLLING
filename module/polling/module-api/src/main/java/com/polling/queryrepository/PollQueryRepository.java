package com.polling.queryrepository;


import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.PollResponseDto;

import java.util.List;

public interface PollQueryRepository {
    List<FindCandidateResponseDto> findCandidatesSortByVoteTotal(Long id);
}