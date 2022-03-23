package com.polling.candidate.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindCandidateResponseDto {
    private Long id;
    private String name;
    private String thumbnail;
    private Integer votesTotalCount;
}
