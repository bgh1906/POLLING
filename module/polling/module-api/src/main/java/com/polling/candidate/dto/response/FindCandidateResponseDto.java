package com.polling.candidate.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindCandidateResponseDto {
    private Long id;
    private String name;
    private String thumbnail;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;
    private Integer votesTotalCount;

    public FindCandidateResponseDto(Long id, String name, String thumbnail, List<String> imagePaths, Integer votesTotalCount) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        imagePath1 = imagePaths.get(0);
        imagePath2 = imagePaths.get(1);
        imagePath3 = imagePaths.get(2);
        this.votesTotalCount = votesTotalCount;
    }
}