package com.polling.poll.dto.candidate.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindAnonymousCandidateResponseDto {
    private Long id;
    private String name;
    private String thumbnail;
    private Integer votesTotalCount;
}
