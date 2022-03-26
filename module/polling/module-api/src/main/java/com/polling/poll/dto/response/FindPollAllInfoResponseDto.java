package com.polling.poll.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.polling.poll.dto.candidate.response.FindCandidateDetailsResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindPollAllInfoResponseDto {
    private List<FindCandidateDetailsResponseDto> candidates;
    String name;
    String content;
    String thumbnail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime endDate;
}
