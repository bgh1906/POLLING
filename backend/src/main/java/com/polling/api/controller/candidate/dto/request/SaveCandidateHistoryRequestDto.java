package com.polling.api.controller.candidate.dto.request;

import com.polling.core.entity.candidate.Candidate;
import com.polling.core.entity.candidate.CandidateHistory;
import com.polling.core.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveCandidateHistoryRequestDto {
    private Long candidateId;
    private Integer voteCount;
    private String transactionId;
}
