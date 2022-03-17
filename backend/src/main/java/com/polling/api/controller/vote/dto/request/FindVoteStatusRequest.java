package com.polling.api.controller.vote.dto.request;

import com.polling.core.entity.vote.status.VoteStatus;
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
