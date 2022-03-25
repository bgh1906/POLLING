package com.polling.candidate.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindCandidateThumbnailResponseDto {
    private Long id;
    private String name;
    private String thumbnail;
    private Integer votesTotalCount;
}
