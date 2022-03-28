package com.polling.poll.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.polling.poll.dto.candidate.request.SaveCandidateRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ModifyPollRequestDto {
    List<SaveCandidateRequestDto> candidateDtos;
    String title;
    String content;
    String thumbnail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime endDate;

    @Builder
    public ModifyPollRequestDto(List<SaveCandidateRequestDto> candidateDtos, String title, String content, String thumbnail, LocalDateTime startDate, LocalDateTime endDate) {
        this.candidateDtos = candidateDtos;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
