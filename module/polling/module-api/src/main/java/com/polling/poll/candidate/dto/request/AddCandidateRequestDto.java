package com.polling.poll.candidate.dto.request;

import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 특정 투표에 후보자 추가 요청 DTO
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AddCandidateRequestDto {

  @NotNull
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
