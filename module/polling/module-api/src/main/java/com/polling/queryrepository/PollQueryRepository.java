package com.polling.queryrepository;


import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.response.FindPollPageResponseDto;
import com.polling.poll.dto.response.FindPollResponseDto;

import java.util.List;

public interface PollQueryRepository {
    List<FindCandidateResponseDto> findCandidatesSortByVoteTotal(Long id);
    List<FindPollPageResponseDto> findPollPageByStatus(PollStatus pollStatus, int offset, int limit);
}