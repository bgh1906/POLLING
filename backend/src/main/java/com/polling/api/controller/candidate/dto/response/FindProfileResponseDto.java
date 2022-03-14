package com.polling.api.controller.candidate.dto.response;

import com.polling.api.controller.candidate.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindProfileResponseDto {
    private String name;
    private String content;
    private String profilePath;
    private Long voteTotal;
    private List<CommentDto> comments;
}
