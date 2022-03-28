package com.polling.poll.dto.candidate.response;


import com.polling.entity.candidate.Candidate;
import com.polling.poll.dto.comment.response.FindCommentResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCandidateDetailsResponseDto {

  private String name;
  private String profile;
  private String thumbnail;
  private String imagePath1;
  private String imagePath2;
  private String imagePath3;
  private Integer voteTotalCount;
  private List<FindCommentResponseDto> comments;

  public static FindCandidateDetailsResponseDto of(Candidate candidate,
      List<FindCommentResponseDto> comments) {
    return new FindCandidateDetailsResponseDto(candidate.getName(),
        candidate.getProfile(),
        candidate.getThumbnail(),
        candidate.getGalleries().get(0).getImagePath(),
        candidate.getGalleries().get(1).getImagePath(),
        candidate.getGalleries().get(2).getImagePath(),
        candidate.getVoteTotalCount(),
        comments);
  }
}
