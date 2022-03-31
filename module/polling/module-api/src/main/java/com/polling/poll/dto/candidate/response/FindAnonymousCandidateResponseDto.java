package com.polling.poll.dto.candidate.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 간략한 투표자의 정보를 반환하는 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindAnonymousCandidateResponseDto {

  private Long candidateId;
  private Integer candidateIndex;
  private String name;
  private String thumbnail;

}
