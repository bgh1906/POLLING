package com.polling.core.entity.vote;

import com.polling.core.entity.common.BaseTimeEntity;
import com.polling.core.entity.user.User;
import com.polling.core.entity.vote.status.VoteStatus;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private User host;

    @Column
    private String content;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private VoteStatus voteStatus = VoteStatus.WAIT;

    @Builder
    public Vote(String name, User host, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.host = host;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setVoteStatus(VoteStatus voteStatus){
        this.voteStatus = voteStatus;
    }
}
