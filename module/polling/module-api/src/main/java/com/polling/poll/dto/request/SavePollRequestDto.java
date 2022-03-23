package com.polling.poll.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.polling.candidate.dto.request.SaveCandidateRequestDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.poll.Poll;
import com.polling.entity.poll.status.ShowStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SavePollRequestDto {
    List<SaveCandidateRequestDto> candidateDtos;
    @NotNull
    String title;
    @NotNull
    String content;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime startDate;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime endDate;

    @NotNull
    ShowStatus showStatus;

    @Builder
    public SavePollRequestDto(List<SaveCandidateRequestDto> candidateDtos, String title, String content, LocalDateTime startDate, LocalDateTime endDate, ShowStatus showStatus){
        this.candidateDtos = candidateDtos;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.showStatus = showStatus;
    }

}
