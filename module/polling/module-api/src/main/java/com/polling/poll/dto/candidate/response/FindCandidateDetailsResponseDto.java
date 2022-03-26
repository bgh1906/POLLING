package com.polling.poll.dto.candidate.response;


import com.polling.poll.dto.comment.request.CommentDto;
import com.polling.entity.candidate.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCandidateDetailsResponseDto {
    private String name;
    private String content;
    private String thumbnail;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;
    private Integer voteTotalCount;
    private List<CommentDto> comments;

    public static FindCandidateDetailsResponseDto of(Candidate candidate, List<CommentDto> comments){
        return new FindCandidateDetailsResponseDto(candidate.getName(),
                candidate.getProfile(),
                candidate.getThumbnail(),
                candidate.getImagePaths().get(0),
                candidate.getImagePaths().get(1),
                candidate.getImagePaths().get(2),
                candidate.getVoteTotalCount(),
                comments);
    }
}
