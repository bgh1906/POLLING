package com.polling.core.entity.member;

import com.polling.core.entity.common.BaseTimeEntity;
import com.polling.core.entity.member.status.MemberRole;
import com.polling.core.entity.member.status.OAuthType;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_USER")
@Entity
@QueryEntity
public class Member extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<MemberRole> memberRole;

    @Enumerated(EnumType.STRING)
    private OAuthType oauthType;

    private Long oauthId;

    @Builder
    public Member(String nickname, String email, String password, String phoneNumber, OAuthType oauthType){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.oauthType = oauthType;
        memberRole = new HashSet<>();
        memberRole.add(MemberRole.ROLE_USER);
    }

    public void nameUpdate(String nickname){
        this.nickname = nickname;
    }

    public void emailUpdate(String email){
        this.email = email;
    }

    public void passwordUpdate(String password){
        this.password = password;
    }

    public void phoneNumberUpdate(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void changeMemberRole(Set<MemberRole> memberRole){
        this.memberRole = memberRole;
    }

}
