package com.ssafy.domain.candidate.entity;

import com.ssafy.domain.candidatehistory.entity.CandidateHistory;
import com.ssafy.domain.comment.entity.Comment;
import com.ssafy.domain.common.BaseTimeEntity;
import com.ssafy.domain.vote.entity.Vote;
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
public class Candidate extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "candidate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @Column(name = "candidate_name")
    private String name;

    @Column
    private String profilePath;

    @Column
    private Long voteTotal;

    @OneToMany(mappedBy = "comment_id", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "candidate_history_id", cascade = CascadeType.PERSIST)
    private List<CandidateHistory> candidateHistories = new ArrayList<CandidateHistory>();

    @Builder
    public Candidate(Vote vote, String name, String profilePath, Long voteTotal){
        this.vote = vote;
        this.name = name;
        this.profilePath = profilePath;
        this.voteTotal = voteTotal;
    }

}
