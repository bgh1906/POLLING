package com.ssafy.api.controller.candidate.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindVoteHistoryResponseDto {
    private String user_name;
    private int voteCount;
    private String transactionId;
}
