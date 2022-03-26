package com.polling.poll.dto.candidate.request;

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
    private Long id;
    private String name;
    private String profile;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;
    private String thumbnail;

    @Builder
    public SaveCandidateRequestDto(Long id, String name, String profile, String imagePath1, String imagePath2, String imagePath3, String thumbnail) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.imagePath1 = imagePath1;
        this.imagePath2 = imagePath2;
        this.imagePath3 = imagePath3;
        this.thumbnail = thumbnail;
    }

    public Candidate toEntity(){
        List<String> imagePaths = new ArrayList<>();
        imagePaths.add(imagePath1);
        imagePaths.add(imagePath2);
        imagePaths.add(imagePath3);

        return Candidate.builder()
                .name(name)
                .profile(profile)
                .imagePaths(imagePaths)
                .thumbnail(thumbnail)
                .build();
    }
}
