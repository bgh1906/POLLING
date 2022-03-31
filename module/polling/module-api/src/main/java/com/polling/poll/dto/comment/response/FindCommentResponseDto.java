package com.polling.poll.dto.comment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 특정 후보자의 모든 댓글 정보를 반환하는 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCommentResponseDto {

  private Long commentId;
  private Long memberId;
  private String memberNickname;
  private String content;
}
