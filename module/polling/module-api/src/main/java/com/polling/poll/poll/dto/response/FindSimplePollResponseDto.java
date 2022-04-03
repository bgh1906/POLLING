package com.polling.poll.poll.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.polling.entity.poll.Poll;
import com.polling.poll.poll.dto.candidate.response.FindAnonymousCandidateResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 일반 유저가 볼 수 있는 특정 투표의 모든 정보
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindSimplePollResponseDto {

  String title;
  String content;
  String thumbnail;
  Boolean openStatus;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime endDate;
  private List<FindAnonymousCandidateResponseDto> candidates;

  public static FindSimplePollResponseDto of(List<FindAnonymousCandidateResponseDto> candidates,
      Poll poll) {
    return new FindSimplePollResponseDto(
        poll.getTitle(),
        poll.getContent(),
        poll.getThumbnail(),
        poll.getOpenStatus(),
        poll.getStartDate(),
        poll.getEndDate(),
        candidates);
  }
}
