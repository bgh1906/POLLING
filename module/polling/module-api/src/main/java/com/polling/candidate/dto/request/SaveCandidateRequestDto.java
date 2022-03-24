package com.polling.candidate.dto.request;

import com.polling.entity.candidate.Candidate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveCandidateRequestDto {
    private String name;
    private String profile;
    private List<String> imagePaths;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;
    private String thumbnail;

    @Builder
    public SaveCandidateRequestDto(String name, String profile, List<String> imagePaths, String imagePath1, String imagePath2, String imagePath3, String thumbnail) {
        this.name = name;
        this.profile = profile;
        this.imagePaths = imagePaths;
        this.imagePath1 = imagePath1;
        this.imagePath2 = imagePath2;
        this.imagePath3 = imagePath3;
        this.thumbnail = thumbnail;
    }

    public Candidate toEntity(){
        List<String> imagePaths = new ArrayList<>();
        if(imagePath1 != null)
            imagePaths.add(imagePath1);
        if(imagePath2 != null)
            imagePaths.add(imagePath2);
        if(imagePath3 != null)
            imagePaths.add(imagePath3);

        return Candidate.builder()
                .name(name)
                .profile(profile)
                .imagePaths(imagePaths)
                .thumbnail(thumbnail)
                .build();
    }
}
