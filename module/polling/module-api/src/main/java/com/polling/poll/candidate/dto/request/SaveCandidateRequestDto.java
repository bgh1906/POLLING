package com.polling.poll.candidate.dto.request;


import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이거 나중에 지워야함
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveCandidateRequestDto {

  @NotNull
  private String name;
  private String profile;
  private String imagePath1;
  private String imagePath2;
  private String imagePath3;
  private String thumbnail;

  @Builder
  public SaveCandidateRequestDto(String name, String profile,
      String imagePath1,
      String imagePath2, String imagePath3, String thumbnail) {
    this.name = name;
    this.profile = profile;
    this.imagePath1 = imagePath1;
    this.imagePath2 = imagePath2;
    this.imagePath3 = imagePath3;
    this.thumbnail = thumbnail;
  }

  public Candidate toEntity() {
    return Candidate.createCandidate(name, profile, thumbnail, imagePath1, imagePath2, imagePath3);
  }
}
