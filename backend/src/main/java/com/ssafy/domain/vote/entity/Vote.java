package com.ssafy.domain.vote.entity;

import com.ssafy.domain.candidate.entity.Candidate;
import com.ssafy.domain.common.BaseTimeEntity;
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
