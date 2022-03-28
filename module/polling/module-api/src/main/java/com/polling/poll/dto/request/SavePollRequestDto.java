package com.polling.poll.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.polling.poll.dto.candidate.request.SaveCandidateRequestDto;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime startDate;
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  LocalDateTime endDate;

  @Builder
  public SavePollRequestDto(List<SaveCandidateRequestDto> candidateDtos, String title,
      String content, String thumbnail, LocalDateTime startDate, LocalDateTime endDate) {
    this.candidateDtos = candidateDtos;
    this.title = title;
    this.content = content;
    this.thumbnail = thumbnail;
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
