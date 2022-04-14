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
    return Candidate.createCandidate(name, profile, thumbnail, imagePath1, imagePath2, imagePath3);
  }
}
