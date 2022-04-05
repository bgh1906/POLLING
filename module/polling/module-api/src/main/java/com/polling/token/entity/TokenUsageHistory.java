package com.polling.token.entity;

import com.polling.member.entity.Member;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
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
public class TokenUsageHistory {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "history_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "image_id")
  private CandidateGallery gallery;


  @Builder
  public TokenUsageHistory(Member member, Candidate candidate,
      CandidateGallery gallery) {
    this.member = member;
    this.candidate = candidate;
    this.gallery = gallery;
  }
}
