package com.polling.poll.dto.request;

import com.polling.entity.poll.status.PollStatus;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FindPollStatusRequest {

  @NotNull
  PollStatus status;
}
