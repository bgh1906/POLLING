package com.polling.poll.dto.candidate.response;

import com.polling.entity.candidate.CandidateGallery;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindAdminCandidateResponseDto {

  private Long candidateId;
  private Integer candidateIndex;
  private String name;
  private String profile;
  private String thumbnail;
  private String imagePath1;
  private String imagePath2;
  private String imagePath3;

  @Builder
  public FindAdminCandidateResponseDto(Long candidateId, Integer candidateIndex, String name,
      String thumbnail,
      List<CandidateGallery> galleries, String profile) {
    this.candidateId = candidateId;
    this.candidateIndex = candidateIndex;
    this.name = name;
    this.profile = profile;
    this.thumbnail = thumbnail;
    imagePath1 = galleries.get(0).getImagePath();
    imagePath2 = galleries.get(1).getImagePath();
    imagePath3 = galleries.get(2).getImagePath();
  }
}