package com.polling.poll.dto.candidate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCandidateHistoryResponseDto {
    private String userName;
    private int voteCount;
    private String transactionId;
}
