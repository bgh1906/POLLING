package com.polling.entity.member;

import com.polling.entity.common.BaseTimeEntity;
import com.polling.entity.member.status.MemberRole;
import com.polling.entity.member.status.OAuthType;
import com.querydsl.core.annotations.QueryEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

  private String oauthId;

  @Enumerated(EnumType.STRING)
  private OAuthType oauthType;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private Set<MemberRole> memberRole;

  @Builder
  public Member(String nickname, String email, String password, String phoneNumber,
      OAuthType oauthType, String oauthId) {
    this.nickname = nickname;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.oauthType = oauthType;
    this.oauthId = oauthId;
    memberRole = new HashSet<>();
    memberRole.add(MemberRole.ROLE_USER);
  }

  public void changeNickname(String nickname) {
    this.nickname = nickname;
  }

  public void changePassword(String password) {
    this.password = password;
  }

  public void changeMemberRole(Set<MemberRole> memberRole) {
    this.memberRole = memberRole;
  }

  public void addRole(MemberRole role) {
    this.memberRole.add(role);
  }
}
