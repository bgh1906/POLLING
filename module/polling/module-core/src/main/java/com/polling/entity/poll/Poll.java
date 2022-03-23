package com.polling.entity.poll;


import com.polling.entity.candidate.Candidate;
import com.polling.entity.common.BaseTimeEntity;
import com.polling.entity.member.Member;
import com.polling.entity.poll.status.PollStatus;
import com.polling.entity.poll.status.ShowStatus;
import com.querydsl.core.annotations.QueryEntity;
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
public class Poll extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "poll_id")
    private Long id;

    @Column(name = "poll_name")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member pollCreator;

    @Column
    private String content;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private PollStatus pollStatus = PollStatus.UNAPPROVED;

    @Column
    private ShowStatus showStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "poll", cascade = CascadeType.PERSIST)
    private List<Candidate> candidates = new ArrayList<>();

    @Builder
    public Poll(String title, Member pollCreator, String content, LocalDateTime startDate, LocalDateTime endDate, ShowStatus showStatus) {
        this.title = title;
        this.pollCreator = pollCreator;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.showStatus = showStatus;
    }

    public void changePollStatus(PollStatus pollStatus){
        this.pollStatus = pollStatus;
    }

    public void changePeriod(LocalDateTime startDate, LocalDateTime endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void changeDescription(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void addCandidate(Candidate candidate){
        this.candidates.add(candidate);
        candidate.changePoll(this);
    }
}
