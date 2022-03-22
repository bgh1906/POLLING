package com.polling.entity.poll;


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
    private Member host;

    @Column
    private String content;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private PollStatus pollStatus = PollStatus.WAIT;

    @Column
    private ShowStatus showStatus;

    @Builder
    public Poll(String title, Member host, String content, LocalDateTime startDate, LocalDateTime endDate, ShowStatus showStatus) {
        this.title = title;
        this.host = host;
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
}
