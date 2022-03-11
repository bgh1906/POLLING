package com.ssafy.api.controller.candidate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindProfileResponseDto {
    private String name;
    private String profile_path;
    private Long voteTotal;
}
