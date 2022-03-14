package com.ssafy.api.controller.vote.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.api.controller.candidate.dto.request.SaveCandidateRequestDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaveVoteRequestDto {
    List<SaveCandidateRequestDto> candidates;
    @NotNull
    String name;
    @NotNull
    String content;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime startDate;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime endDate;
    Long hostId;

    @Builder
    public SaveVoteRequestDto(List<SaveCandidateRequestDto> candidates, String name, String content, LocalDateTime startDate, LocalDateTime endDate, Long hostId){
        this.candidates = candidates;
        this.name = name;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hostId = hostId;
    }

}
