package com.polling.candidate.dto.request;

import com.polling.entity.candidate.Candidate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveCandidateRequestDto {
    private String name;
    private String profile;
    private List<String> imagePaths;

    @Builder
    public SaveCandidateRequestDto(String name, String content, List<String> imagePaths){
        this.name = name;
        this.profile = content;
        this.imagePaths = imagePaths;
    }

    public Candidate toEntity(){
        return Candidate.builder()
                .name(name)
                .profile(profile)
                .imagePaths(imagePaths)
                .build();
    }
}
