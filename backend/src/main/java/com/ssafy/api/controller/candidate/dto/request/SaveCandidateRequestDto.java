package com.ssafy.api.controller.candidate.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveCandidateRequestDto {
    private Long voteId;
    private String name;
    private String profile_path;
}
