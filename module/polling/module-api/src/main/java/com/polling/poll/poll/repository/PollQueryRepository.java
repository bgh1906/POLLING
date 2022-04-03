package com.polling.poll.poll.repository;

import com.polling.poll.poll.dto.response.FindPollPageResponseDto;
import com.polling.poll.poll.entity.Poll;
import com.polling.poll.poll.entity.status.PollStatus;
import java.time.LocalDateTime;
import java.util.List;

public interface PollQueryRepository {

  List<FindPollPageResponseDto> findPollPageByStatus(PollStatus pollStatus,
      int offset,
      int limit);

  List<Poll> findByCurrentBeforeEndTime(LocalDateTime current);

  List<Poll> findByCurrentBeforeStartTime(LocalDateTime current);

  void deleteImageByPollId(Long pollId);

  void deleteCandidateByPollId(Long pollId);
}