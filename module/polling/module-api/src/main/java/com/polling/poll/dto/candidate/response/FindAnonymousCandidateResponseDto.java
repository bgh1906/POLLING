package com.polling.poll.dto.candidate.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindAnonymousCandidateResponseDto {

  private Long candidateId;
  private String name;
  private String thumbnail;
  private Integer votesTotalCount;
  private Integer rank;

  public FindAnonymousCandidateResponseDto(Long candidateId, String name, String thumbnail,
      Integer votesTotalCount) {
    this.candidateId = candidateId;
    this.name = name;
    this.thumbnail = thumbnail;
    this.votesTotalCount = votesTotalCount;
  }
}
