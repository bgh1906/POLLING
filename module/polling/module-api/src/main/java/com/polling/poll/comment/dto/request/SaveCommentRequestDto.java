package com.polling.poll.comment.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 특정 후보자에 응원 댓글 저장 요청 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveCommentRequestDto {

  private Long candidateId;
  private String content;

  @Builder
  public SaveCommentRequestDto(Long candidateId, String content) {
    this.candidateId = candidateId;
    this.content = content;
  }
}
