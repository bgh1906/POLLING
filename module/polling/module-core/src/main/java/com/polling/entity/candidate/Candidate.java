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

    private Integer voteTotal;

    @Column
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> imagePaths = new ArrayList<>();

    @Builder
    public Candidate(Poll poll, String name, String profile, List<String> imagePaths){
        this.poll = poll;
        this.name = name;
        this.profile = profile;
        this.imagePaths = imagePaths;
        voteTotal = 0;
    }

    public void addVoteTotal(int numOfVotes){
        voteTotal += numOfVotes;
    }

    public void changePoll(Poll poll){
        this.poll = poll;
    }


}
