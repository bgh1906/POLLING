package com.polling.core.entity.comment;

import com.polling.core.entity.candidate.Candidate;
import com.polling.core.entity.common.BaseTimeEntity;
import com.polling.core.entity.user.User;
import com.querydsl.core.annotations.QueryEntity;
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

    public void updateContent(String content){
        if(content != null){
            this.content = content;
        }
    }

}
