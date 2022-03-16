package com.polling.api.controller.candidate.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveCommentRequestDto {
    private Long candidateId;
    private String content;

    @Builder
    public SaveCommentRequestDto(Long candidateId, String content){
        this.candidateId = candidateId;
        this.content = content;
    }
}
