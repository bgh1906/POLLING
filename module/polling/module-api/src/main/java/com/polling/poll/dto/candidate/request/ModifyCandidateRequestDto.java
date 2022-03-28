package com.polling.poll.dto.candidate.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ModifyCandidateRequestDto {

  private String name;
  private String profile;
  private String imagePath1;
  private String imagePath2;
  private String imagePath3;
  private String thumbnail;
}
