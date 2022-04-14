package com.polling.poll.candidate.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 특정 후보자의 득표 내역 저장 요청 DTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaveCandidateHistoryRequestDto {

  @NotNull
  private Long candidateId;
  @NotNull
  private String transactionId;
  @NotNull
  private Integer voteCount;
}
