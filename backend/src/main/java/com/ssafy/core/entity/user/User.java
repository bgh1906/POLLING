package com.ssafy.core.entity.user;

import com.querydsl.core.annotations.QueryEntity;
import com.ssafy.core.entity.candidate.CandidateHistory;
import com.ssafy.core.entity.comment.Comment;
import com.ssafy.core.entity.common.BaseTimeEntity;
import com.ssafy.core.entity.user.status.OAuthType;
import com.ssafy.core.entity.user.status.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_USER")
@Entity
@QueryEntity
public class User extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "phone_number")
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column
    @Enumerated(EnumType.STRING)
    private OAuthType oauthType;

    @Column
    private Long oauthId;

    @OneToMany(mappedBy = "comment_id", cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "candidate_history_id", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CandidateHistory> candidateHistories = new ArrayList<CandidateHistory>();

    @Builder
    public User(String name, String email, String password, String phone, UserRole userRole, OAuthType oauthType, Long oauthId){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userRole = userRole;
        this.oauthType = oauthType;
        this.oauthId = oauthId;
    }

}
