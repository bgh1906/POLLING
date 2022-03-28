package com.polling.poll.dto.candidate.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AddVoteCountRequestDto {

  private Long candidateId;
  private Integer voteCount;

  @Builder
  public AddVoteCountRequestDto(Long candidateId, Integer voteCount) {
    this.candidateId = candidateId;
    this.voteCount = voteCount;
  }
}
