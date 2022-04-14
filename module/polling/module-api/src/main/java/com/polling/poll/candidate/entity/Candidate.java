package com.polling.poll.candidate.entity;

import com.polling.common.entity.BaseTimeEntity;
import com.polling.poll.poll.entity.Poll;
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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@QueryEntity
public class Candidate extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "candidate_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "vote_id")
  private Poll poll;

  private String name;

  private String profile;

  @Column(length = 1000)
  private String thumbnail;

  private Integer contractIndex;

  @OneToMany(mappedBy = "candidate", cascade = CascadeType.PERSIST)
  private final List<CandidateHistory> histories = new ArrayList<>();

  @Column(length = 1000)
  @OneToMany(mappedBy = "candidate", cascade = CascadeType.PERSIST)
  private final List<CandidateGallery> galleries = new ArrayList<>();

  @Builder
  public Candidate(Integer contractIndex, Poll poll, String name, String profile,
      String thumbnail) {
    this.contractIndex = contractIndex;
    this.poll = poll;
    this.name = name;
    this.profile = profile;
    this.thumbnail = thumbnail;
  }

  public static Candidate createCandidate(String name, String profile, String thumbnail,
      String... imagePaths) {
    Candidate candidate = Candidate.builder()
        .name(name)
        .profile(profile)
        .thumbnail(thumbnail)
        .build();
    for (String imagePath : imagePaths) {
      candidate.addGallery(CandidateGallery.createImage(imagePath));
    }
    return candidate;
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

  public void addHistory(CandidateHistory history) {
    this.histories.add(history);
    history.changeCandidate(this);
  }

  public void changeContractIndex(Integer contractIndex) {
    this.contractIndex = contractIndex;
  }
}
