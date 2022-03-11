package com.ssafy.core.entity.candidate;

import com.querydsl.core.annotations.QueryEntity;
import com.ssafy.core.entity.user.User;
import com.ssafy.core.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_CANDIDATEHISTORY")
@Entity
@QueryEntity
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
