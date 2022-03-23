package com.polling.queryrepository;


import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.poll.dto.response.FindPollPageResponseDto;
import com.polling.poll.dto.response.FindPollResponseDto;

import java.util.List;

public interface PollQueryRepository {
    List<FindCandidateResponseDto> findCandidatesSortByVoteTotal(Long id);
    List<FindPollPageResponseDto> findProgressPollPage(int offset, int limit);
    List<FindPollPageResponseDto> findDonePollPage(int offset, int limit);
}