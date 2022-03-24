package com.polling.entity.candidate;


import com.polling.entity.common.BaseTimeEntity;
import com.polling.entity.poll.Poll;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_CANDIDATE")
@Entity
@QueryEntity
public class Candidate extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "candidate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Poll poll;

    @Column(name = "candidate_name")
    private String name;

    private String profile;

    private Integer voteTotalCount;

    private String thumbnail;

    @Column
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> imagePaths = new ArrayList<>();

    @Builder
    public Candidate(Poll poll, String name, String profile, String thumbnail, List<String> imagePaths){
        this.poll = poll;
        this.name = name;
        this.profile = profile;
        this.thumbnail = thumbnail;
        this.imagePaths = imagePaths;
        voteTotalCount = 0;
    }

    public void addVoteTotal(int numOfVotes){
        voteTotalCount += numOfVotes;
    }

    public void changePoll(Poll poll){
        this.poll = poll;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void changeProfile(String profile){
        this.profile = profile;
    }

    public void changeThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public void changeImagePaths(List<String> imagePaths){
        this.imagePaths = imagePaths;
    }
}
