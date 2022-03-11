package com.ssafy.api.controller.vote.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.api.controller.candidate.dto.response.FindCandidateResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindVoteResponseDto {
    private List<FindCandidateResponseDto> candidates;
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
}
