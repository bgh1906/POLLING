package com.polling.candidate.dto.response;


import com.polling.candidate.dto.CommentDto;
import com.polling.entity.candidate.Candidate;
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
    private String thumbnail;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;
    private Integer voteTotal;
    private List<CommentDto> comments;

    public static FindProfileResponseDto of(Candidate candidate, List<CommentDto> comments){
        return new FindProfileResponseDto(candidate.getName(),
                candidate.getProfile(),
                candidate.getThumbnail(),
                candidate.getImagePaths().get(0),
                candidate.getImagePaths().get(1),
                candidate.getImagePaths().get(2),
                candidate.getVoteTotalCount(),
                comments);
    }
}
