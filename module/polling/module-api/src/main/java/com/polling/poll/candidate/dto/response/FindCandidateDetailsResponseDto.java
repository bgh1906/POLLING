package com.polling.poll.candidate.dto.response;


import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.comment.dto.response.FindCommentResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 일반 유저가 볼 수 있는 후보자의 모든 정보를 반환하는 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindCandidateDetailsResponseDto {

  private Integer candidateIndex;
  private String name;
  private String profile;
  private String thumbnail;
  private String imagePath1;
  private String imagePath2;
  private String imagePath3;

  public static FindCandidateDetailsResponseDto of(Candidate candidate) {
    return new FindCandidateDetailsResponseDto(
        candidate.getContractIndex(),
        candidate.getName(),
        candidate.getProfile(),
        candidate.getThumbnail(),
        candidate.getGalleries().get(0).getImagePath(),
        candidate.getGalleries().get(1).getImagePath(),
        candidate.getGalleries().get(2).getImagePath());
  }
}
