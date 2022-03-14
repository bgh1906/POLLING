package com.ssafy.api.controller.candidate.dto.request;

import com.ssafy.core.entity.candidate.Candidate;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveCandidateRequestDto {
    private String name;
    private String profilePath;

    @Builder
    public SaveCandidateRequestDto(String name, String profilePath){
        this.name = name;
        this.profilePath = profilePath;
    }

    public Candidate toEntity(){
        return Candidate.builder()
                .name(name)
                .profilePath(profilePath)
                .build();
    }
}
