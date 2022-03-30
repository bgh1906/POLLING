package com.polling.poll.dto.candidate.request;

import com.polling.entity.candidate.Candidate;
import com.polling.entity.candidate.CandidateGallery;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AddCandidateRequestDto {

  private Long pollId;
  private String name;
  private String profile;
  private String imagePath1;
  private String imagePath2;
  private String imagePath3;
  private String thumbnail;

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
