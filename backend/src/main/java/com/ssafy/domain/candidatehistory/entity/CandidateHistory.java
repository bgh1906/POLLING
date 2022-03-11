package com.ssafy.domain.candidatehistory.entity;

import com.ssafy.domain.candidate.entity.Candidate;
import com.ssafy.domain.common.BaseTimeEntity;
import com.ssafy.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_CANDIDATEHISTORY")
@Entity
public class CandidateHistory extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "candidate_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column
    private String voteCount;

    @Column
    private String transactionId;

    @Builder
    public CandidateHistory(User user, Candidate candidate, String voteCount, String transactionId){
        this.user = user;
        this.candidate = candidate;
        this.voteCount = voteCount;
        this.transactionId = transactionId;
    }

}
