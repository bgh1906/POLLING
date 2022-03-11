package com.ssafy.core.entity.vote;

import com.querydsl.core.annotations.QueryEntity;
import com.ssafy.core.entity.candidate.Candidate;
import com.ssafy.core.entity.common.BaseTimeEntity;
import com.ssafy.core.entity.vote.status.VoteStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_VOTE")
@Entity
@QueryEntity
public class Vote extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "vote_id")
    private Long id;

    @Column(name = "vote_name")
    private String name;

    @Column(name = "vote_host")
    private String host;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private VoteStatus voteStatus;

    @OneToMany(mappedBy = "candidate_id", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Candidate> candidates = new ArrayList<Candidate>();

    @Builder
    public Vote(String name, String host, LocalDateTime startDate, LocalDateTime end_date){
        this.name = name;
        this.host = host;
        this.startDate = startDate;
        this.endDate = end_date;
        this.voteStatus = VoteStatus.WAIT;
    }

}
