package com.polling.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long commentId;
    private Long memberId;
    private String memberNickname;
    private String content;
}
