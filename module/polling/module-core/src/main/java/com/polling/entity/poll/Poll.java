package com.polling.entity.poll;


import com.polling.entity.candidate.Candidate;
import com.polling.entity.common.BaseTimeEntity;
import com.polling.entity.member.Member;
import com.polling.entity.poll.status.PollStatus;
import com.querydsl.core.annotations.QueryEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_VOTE")
@Entity
@QueryEntity
public class Poll extends BaseTimeEntity {

  @OneToMany(mappedBy = "poll", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private final List<Candidate> candidates = new ArrayList<>();
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "poll_id")
  private Long id;
  @Column(name = "poll_name")
  private String title;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "host_id")
  private Member pollCreator;
  private String content;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
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
