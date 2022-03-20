package com.polling.candidate.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
