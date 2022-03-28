package com.polling.poll.dto.candidate.response;

import com.polling.entity.candidate.CandidateGallery;
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
    private String profile;
    private String thumbnail;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;

    @Builder
    public FindAdminCandidateResponseDto(Long id, String name, String thumbnail, List<CandidateGallery> galleries, String profile) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.thumbnail = thumbnail;
        imagePath1 = galleries.get(0).getImagePath();
        imagePath2 = galleries.get(1).getImagePath();
        imagePath3 = galleries.get(2).getImagePath();
    }
}