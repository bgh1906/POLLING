package com.polling.poll.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
