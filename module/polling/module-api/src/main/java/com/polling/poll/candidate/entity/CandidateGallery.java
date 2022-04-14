package com.polling.poll.candidate.entity;

import com.polling.common.entity.BaseTimeEntity;
import com.querydsl.core.annotations.QueryEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@QueryEntity
public class CandidateGallery extends BaseTimeEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "image_id")
  private Long id;

  @Column(length = 1000)
  String imagePath;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  @Builder
  public CandidateGallery(String imagePath) {
    this.imagePath = imagePath;
  }

  public static CandidateGallery createImage(String imagePath) {
    return CandidateGallery.builder()
        .imagePath(imagePath)
        .build();
  }

  public void changeCandidate(Candidate candidate) {
    this.candidate = candidate;
  }
}
