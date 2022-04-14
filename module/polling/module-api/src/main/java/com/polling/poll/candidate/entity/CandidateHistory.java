package com.polling.poll.candidate.entity;

import com.polling.common.entity.BaseTimeEntity;
import com.polling.member.entity.Member;
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
public class CandidateHistory extends BaseTimeEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "candidate_history_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  private int voteCount;

  private String transactionId;

  @Builder
  public CandidateHistory(Member member, Candidate candidate, int voteCount,
      String transactionId) {
    this.member = member;
    this.candidate = candidate;
    this.voteCount = voteCount;
    this.transactionId = transactionId;
  }

  public static CandidateHistory createCandidateHistory(Member member, Candidate candidate,
      int voteCount, String transactionId) {
    return CandidateHistory.builder()
        .member(member)
        .candidate(candidate)
        .voteCount(voteCount)
        .transactionId(transactionId)
        .build();
  }

  public void changeCandidate(Candidate candidate) {
    this.candidate = candidate;
  }
}
