package com.polling.entity.candidate;


import com.polling.entity.common.BaseTimeEntity;
import com.polling.entity.poll.Poll;
import com.querydsl.core.annotations.QueryEntity;
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
@Table(name = "TB_CANDIDATE")
@Entity
@QueryEntity
public class Candidate extends BaseTimeEntity {

  @Column(length = 1000)
  @OneToMany(mappedBy = "candidate", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private final List<CandidateGallery> galleries = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "candidate_id")
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "vote_id")
  private Poll poll;
  @Column(name = "candidate_name")
  private String name;
  private String profile;
  @Column(length = 1000)
  private String thumbnail;
  private Long smartContractIndex;

  @Builder
  public Candidate(Long smartContractIndex, Poll poll, String name, String profile,
      String thumbnail) {
    this.smartContractIndex = smartContractIndex;
    this.poll = poll;
    this.name = name;
    this.profile = profile;
    this.thumbnail = thumbnail;
  }

  public void changePoll(Poll poll) {
    this.poll = poll;
  }

  public void changeName(String name) {
    this.name = name;
  }

  public void changeProfile(String profile) {
    this.profile = profile;
  }

  public void changeThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public void addGallery(CandidateGallery gallery) {
    this.galleries.add(gallery);
    gallery.changeCandidate(this);
  }
}
