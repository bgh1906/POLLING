package com.polling.poll.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.polling.entity.poll.Poll;
import com.polling.poll.dto.candidate.response.FindAnonymousCandidateResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindSimplePollResponseDto {

  String title;
  String content;
  String thumbnail;
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
        poll.getStartDate(),
        poll.getEndDate(),
        candidates);
  }
}
