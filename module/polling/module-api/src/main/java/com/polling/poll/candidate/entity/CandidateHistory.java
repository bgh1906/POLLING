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
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_CANDIDATEHISTORY")
@Entity
@QueryEntity
public class CandidateHistory extends BaseTimeEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "candidate_history_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  @Column
  private Integer voteCount;

  @Column
  private String transactionId;

  @Builder
  public CandidateHistory(Member member, Candidate candidate, Integer voteCount,
      String transactionId) {
    this.member = member;
    this.candidate = candidate;
    this.voteCount = voteCount;
    this.transactionId = transactionId;
  }
}
