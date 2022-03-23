package com.polling.poll.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.polling.candidate.dto.response.FindCandidateResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindPollResponseDto {
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

    @Builder
    public FindPollResponseDto(List<FindCandidateResponseDto> list,
                               String name, String content, LocalDateTime startDate, LocalDateTime endDate){
        this.candidates = list;
        this.content = content;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
