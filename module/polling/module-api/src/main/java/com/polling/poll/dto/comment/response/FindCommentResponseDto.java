package com.polling.poll.dto.comment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCommentResponseDto {

  private Long commentId;
  private Long memberId;
  private String memberNickname;
  private String content;
}
