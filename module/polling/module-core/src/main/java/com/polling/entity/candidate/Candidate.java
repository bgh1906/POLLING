package com.polling.entity.candidate;


import com.polling.entity.common.BaseTimeEntity;
import com.polling.entity.poll.Poll;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column
    private String content;

    @Column
    private Integer voteTotal;

    @Embedded
    private final CandidateInfo candidateInfo = new CandidateInfo();



    @Builder
    public Candidate(Poll poll, String name, String content, String profilePath){
        this.poll = poll;
        this.name = name;
        this.content = content;
        this.candidateInfo.setProfilePath(profilePath);
        voteTotal = 0;
    }

    public void assignVote(Poll poll){
        this.poll = poll;
    }

    public void addVote(int num){
        voteTotal += num;
    }

}
