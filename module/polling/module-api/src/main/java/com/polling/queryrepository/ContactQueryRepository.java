package com.polling.queryrepository;


import com.polling.contact.dto.FindContactResponseDto;
import com.polling.entity.poll.Poll;
import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.response.FindPollPageResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ContactQueryRepository {

  List<FindContactResponseDto> findContactByMemberId(Long memberId);
}