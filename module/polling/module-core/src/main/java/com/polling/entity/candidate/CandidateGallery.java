package com.polling.entity.candidate;

import com.polling.entity.common.BaseTimeEntity;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@QueryEntity
public class CandidateGallery extends BaseTimeEntity {

  String imagePath;
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "image_id")
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  public CandidateGallery(String imagePath) {
    this.imagePath = imagePath;
  }

  public void changeCandidate(Candidate candidate) {
    this.candidate = candidate;
  }
}
