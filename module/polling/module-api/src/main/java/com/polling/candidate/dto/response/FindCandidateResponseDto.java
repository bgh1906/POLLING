package com.polling.candidate.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindCandidateResponseDto {
    private Long id;
    private String name;
    private String profilePath;
    private Integer voteTotal;
}
