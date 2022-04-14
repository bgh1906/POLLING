package com.polling.poll.poll.entity;

import com.polling.common.entity.BaseTimeEntity;
import com.polling.member.entity.Member;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.poll.entity.status.PollStatus;
import com.querydsl.core.annotations.QueryEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@QueryEntity
public class Poll extends BaseTimeEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "poll_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member pollCreator;

  @OneToMany(mappedBy = "poll", cascade = CascadeType.PERSIST)
  private final List<Candidate> candidates = new ArrayList<>();

  private String title;

  private String content;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  @Enumerated(EnumType.STRING)
  private PollStatus pollStatus = PollStatus.UNAPPROVED;

  private Boolean openStatus;

  @Column(length = 1000)
  private String thumbnail;

  @Builder
  public Poll(String title, Member pollCreator, String content, LocalDateTime startDate,
      LocalDateTime endDate, String thumbnail, Boolean openStatus) {
    this.title = title;
    this.pollCreator = pollCreator;
    this.content = content;
    this.startDate = startDate;
    this.endDate = endDate;
    this.thumbnail = thumbnail;
    this.openStatus = openStatus;
  }

  public static Poll createPoll(String title, String content, String thumbnail, Boolean openStatus,
      LocalDateTime startDate, LocalDateTime endDate) {
    return Poll.builder()
        .title(title)
        .content(content)
        .thumbnail(thumbnail)
        .openStatus(openStatus)
        .startDate(startDate)
        .endDate(endDate)
        .build();
  }

  public void changePollStatus(PollStatus pollStatus) {
    this.pollStatus = pollStatus;
  }

  public void changePeriod(LocalDateTime startDate, LocalDateTime endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public void changeDescription(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public void changeThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public void addCandidate(Candidate candidate) {
    this.candidates.add(candidate);
    candidate.changePoll(this);
  }

  public void changeOpenStatus() {
    openStatus = !openStatus;
  }

  public void changeOpenStatus(Boolean openStatus) {
    this.openStatus = openStatus;
  }
}
