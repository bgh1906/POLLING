package com.polling.entity.comment;

import com.polling.entity.candidate.Candidate;
import com.polling.entity.common.BaseTimeEntity;
import com.polling.entity.member.Member;
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
@Table(name = "TB_COMMENT")
@Entity
@QueryEntity
public class Comment extends BaseTimeEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "comment_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  @Column
  private String content;

  @Builder
  public Comment(Member member, Candidate candidate, String content) {
    this.member = member;
    this.candidate = candidate;
    this.content = content;
  }

  public void updateContent(String content) {
    if (content != null) {
      this.content = content;
    }
  }

}
