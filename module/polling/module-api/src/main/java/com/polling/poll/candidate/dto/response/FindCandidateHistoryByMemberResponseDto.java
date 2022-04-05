package com.polling.poll.candidate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 특정 후보자의 득표 내역을 반환하는 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCandidateHistoryByMemberResponseDto {
  private Integer voteCount;
  private String transactionId;
  private LocalDateTime createdDate;
  private String thumbnail;
  private String titie;
  private String name;
}
