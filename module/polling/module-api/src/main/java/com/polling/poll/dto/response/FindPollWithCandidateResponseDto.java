package com.polling.poll.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.polling.entity.poll.Poll;
import com.polling.poll.dto.candidate.response.FindAdminCandidateResponseDto;
import com.polling.poll.dto.candidate.response.FindAnonymousCandidateResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindPollWithCandidateResponseDto {
    private List<FindAdminCandidateResponseDto> candidates;
    String title;
    String content;
    String thumbnail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime endDate;

    public static FindPollWithCandidateResponseDto of(List<FindAdminCandidateResponseDto> candidates, Poll poll){
        return new FindPollWithCandidateResponseDto(candidates,
                poll.getTitle(),
                poll.getContent(),
                poll.getThumbnail(),
                poll.getStartDate(),
                poll.getEndDate());
    }
}
