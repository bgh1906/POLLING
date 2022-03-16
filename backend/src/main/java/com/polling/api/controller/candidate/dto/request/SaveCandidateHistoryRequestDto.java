package com.polling.api.controller.candidate.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveCandidateHistoryRequestDto {
    private Long candidateId;
    private Integer voteCount;
    private String transactionId;

    @Builder
    public SaveCandidateHistoryRequestDto(Long candidateId, Integer voteCount, String transactionId){
        this.candidateId = candidateId;
        this.voteCount = voteCount;
        this.transactionId = transactionId;
    }
}
