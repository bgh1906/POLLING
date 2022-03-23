package com.polling.poll.dto.request;

import com.polling.entity.poll.status.PollStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FindPollStatusRequest {
    @NotNull
    PollStatus status;
}
