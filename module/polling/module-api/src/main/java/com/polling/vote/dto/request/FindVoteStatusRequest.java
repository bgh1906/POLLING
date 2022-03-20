package com.polling.vote.dto.request;

import com.polling.entity.vote.status.VoteStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FindVoteStatusRequest {
    @NotNull
    VoteStatus status;
}
