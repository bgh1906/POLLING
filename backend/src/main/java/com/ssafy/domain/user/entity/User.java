package com.ssafy.domain.user.entity;

import com.ssafy.domain.candidatehistory.entity.CandidateHistory;
import com.ssafy.domain.comment.entity.Comment;
import com.ssafy.domain.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "TB_USER")
@Entity
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
    private String role;

    @Column
    @Enumerated(EnumType.STRING)
    private String oauth_type;

    @Column
    private Long oauth_id;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "candidate_history", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CandidateHistory> candidateHistories = new ArrayList<CandidateHistory>();

    @Builder
    public User(String name, String email, String password, String phone, String role, String oauth_type, Long oauth_id){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.oauth_type = oauth_type;
        this.oauth_id = oauth_id;
    }

}
