package com.polling.api.controller.candidate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindVoteHistoryResponseDto {
    private String userName;
    private int voteCount;
    private String transactionId;
}
