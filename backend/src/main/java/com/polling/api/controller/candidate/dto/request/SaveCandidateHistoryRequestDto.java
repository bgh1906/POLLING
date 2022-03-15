package com.polling.api.controller.candidate.dto.request;

import com.polling.core.entity.candidate.Candidate;
import com.polling.core.entity.candidate.CandidateHistory;
import com.polling.core.entity.user.User;
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
