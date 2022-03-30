package com.polling.poll.dto.candidate.request;

import com.polling.entity.candidate.Candidate;
import com.polling.entity.candidate.CandidateGallery;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveCandidateRequestDto {

  @NotNull
  private String name;
  @NotNull
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
    Candidate candidate = Candidate.builder()
        .name(name)
        .profile(profile)
        .thumbnail(thumbnail)
        .build();
    candidate.addGallery(new CandidateGallery(imagePath1));
    candidate.addGallery(new CandidateGallery(imagePath2));
    candidate.addGallery(new CandidateGallery(imagePath3));

    return candidate;
  }
}
