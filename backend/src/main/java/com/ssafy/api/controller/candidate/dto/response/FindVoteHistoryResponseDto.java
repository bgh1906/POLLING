package com.ssafy.api.controller.candidate.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindVoteHistoryResponseDto {
    private String userName;
    private int voteCount;
    private String transactionId;
}
