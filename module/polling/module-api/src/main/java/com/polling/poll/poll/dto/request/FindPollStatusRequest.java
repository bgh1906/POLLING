package com.polling.poll.poll.dto.request;

import com.polling.entity.poll.status.PollStatus;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 요청된 투표의 상태에 맞는 모든 투표 요청 DTO
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FindPollStatusRequest {

  @NotNull
  PollStatus status;
}
