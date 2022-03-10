package com.ssafy.domain.candidatehistory.entity;

import com.ssafy.domain.candidate.entity.Candidate;
import com.ssafy.domain.common.BaseTimeEntity;
import com.ssafy.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TB_CANDIDATEHISTORY")
@Entity
public class CandidateHistory extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "candidate_history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column
    private String vote_count;

    @Column
    private String transaction_id;

    @Builder
    public CandidateHistory(User user, Candidate candidate, String vote_count, String transaction_id){
        this.user = user;
        this.candidate = candidate;
        this.vote_count = vote_count;
        this.transaction_id = transaction_id;
    }

}
