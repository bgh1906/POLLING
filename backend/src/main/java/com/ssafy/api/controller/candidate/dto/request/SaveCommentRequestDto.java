package com.ssafy.api.controller.candidate.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveCommentRequestDto {
    private Long candidateId;
    private String content;
}
