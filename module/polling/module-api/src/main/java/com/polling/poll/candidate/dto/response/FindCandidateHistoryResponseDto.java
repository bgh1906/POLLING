package com.polling.poll.candidate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 특정 후보자의 득표 내역을 반환하는 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCandidateHistoryResponseDto {

  private String nickname;
  private int voteCount;
  private String transactionId;
}
