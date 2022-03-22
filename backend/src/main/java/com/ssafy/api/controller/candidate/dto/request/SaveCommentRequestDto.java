package com.ssafy.api.controller.candidate.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveCommentRequestDto {
    private Long candidateId;
    private String content;
}