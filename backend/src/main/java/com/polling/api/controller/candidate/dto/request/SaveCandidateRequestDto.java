package com.polling.api.controller.candidate.dto.request;

import com.polling.core.entity.candidate.Candidate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveCandidateRequestDto {
    private String name;
    private String content;
    private String profilePath;

    @Builder
    public SaveCandidateRequestDto(String name, String content, String profilePath){
        this.name = name;
        this.content = content;
        this.profilePath = profilePath;
    }

    public Candidate toEntity(){
        return Candidate.builder()
                .name(name)
                .content(content)
                .profilePath(profilePath)
                .build();
    }
}
