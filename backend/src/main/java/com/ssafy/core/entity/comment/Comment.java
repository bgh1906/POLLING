package com.ssafy.core.entity.comment;

import com.querydsl.core.annotations.QueryEntity;
import com.ssafy.core.entity.user.User;
import com.ssafy.core.entity.candidate.Candidate;
import com.ssafy.core.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_COMMENT")
@Entity
@QueryEntity
public class Comment extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column
    private String content;

    @Builder
    public Comment(User user, Candidate candidate, String content){
        this.user = user;
        this.candidate = candidate;
        this.content = content;
    }

}
