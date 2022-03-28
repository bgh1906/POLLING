package com.polling.poll.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.polling.entity.poll.Poll;
import com.polling.poll.dto.candidate.response.FindAdminCandidateResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 투표 정보 및 참가한 후보자 정보 조회
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindPollWithCandidateResponseDto {

  String title;
  String content;
  String thumbnail;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime endDate;
  private List<FindAdminCandidateResponseDto> candidates;

  public static FindPollWithCandidateResponseDto of(
      List<FindAdminCandidateResponseDto> candidates, Poll poll) {
    return new FindPollWithCandidateResponseDto(
        poll.getTitle(),
        poll.getContent(),
        poll.getThumbnail(),
        poll.getStartDate(),
        poll.getEndDate(),
        candidates);
  }
}
