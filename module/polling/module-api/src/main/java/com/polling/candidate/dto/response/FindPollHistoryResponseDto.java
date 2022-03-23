package com.polling.candidate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPollHistoryResponseDto {
    private String userName;
    private int voteCount;
    private String transactionId;
}
