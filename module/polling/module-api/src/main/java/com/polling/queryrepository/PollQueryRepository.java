package com.polling.queryrepository;


import com.polling.entity.poll.Poll;
import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.response.FindPollPageResponseDto;
import java.time.LocalDateTime;
import java.util.List;

public interface PollQueryRepository {

  List<FindPollPageResponseDto> findPollPageByStatus(PollStatus pollStatus,
      int offset,
      int limit);

  List<Poll> findByCurrentBeforeEndTime(LocalDateTime current);
}