package com.polling.poll.dto.candidate.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindAdminCandidateResponseDto {
    private Long id;
    private String name;
    private String thumbnail;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;
    private String profile;

    @Builder
    public FindAdminCandidateResponseDto(Long id, String name, String thumbnail, List<String> imagePaths, String profile) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        imagePath1 = imagePaths.get(0);
        imagePath2 = imagePaths.get(1);
        imagePath3 = imagePaths.get(2);
        this.profile = profile;
    }
}