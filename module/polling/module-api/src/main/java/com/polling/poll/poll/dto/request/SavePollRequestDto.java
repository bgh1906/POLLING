package com.polling.poll.poll.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.polling.poll.candidate.dto.request.SaveCandidateRequestDto;
import com.polling.poll.poll.entity.Poll;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 투표 및 포함된 후보자들을 저장 요청 DTO
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SavePollRequestDto {

  List<SaveCandidateRequestDto> candidateDtos;
  @NotNull
  String title;
  @NotNull
  String content;
  @NotNull
  String thumbnail;
  @NotNull
  Boolean openStatus;
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime startDate;
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime endDate;

  public Poll toPollEntity() {
    return Poll.createPoll(title, content, thumbnail, openStatus, startDate, endDate);
  }
}
