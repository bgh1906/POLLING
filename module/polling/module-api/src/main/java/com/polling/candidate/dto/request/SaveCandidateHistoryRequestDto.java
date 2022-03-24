package com.polling.candidate.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveCandidateHistoryRequestDto {
    private Long candidateId;
    private Integer voteCount;

    @Builder
    public SaveCandidateHistoryRequestDto(Long candidateId, Integer voteCount){
        this.candidateId = candidateId;
        this.voteCount = voteCount;
    }
}
