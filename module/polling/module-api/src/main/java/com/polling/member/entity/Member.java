package com.polling.member.entity;

import com.polling.common.entity.BaseTimeEntity;
import com.polling.member.entity.status.MemberRole;
import com.polling.member.entity.status.OAuthType;
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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@QueryEntity
public class Member extends BaseTimeEntity {

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private final Set<MemberRole> memberRole = new HashSet<>();
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "member_id")
  private Long id;
  private String wallet;
  private String nickname;
  private String email;
  private String password;
  private String phoneNumber;
  private String oauthId;
  @Enumerated(EnumType.STRING)
  private OAuthType oauthType;

  @Builder
  public Member(String nickname, String wallet, String email, String password, String phoneNumber,
      OAuthType oauthType, String oauthId) {
    this.wallet = wallet;
    this.nickname = nickname;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.oauthType = oauthType;
    this.oauthId = oauthId;
    memberRole.add(MemberRole.ROLE_USER);
  }

  public void changeNickname(String nickname) {
    this.nickname = nickname;
  }

  public void changePassword(String password) {
    this.password = password;
  }

  public void addRole(MemberRole role) {
    this.memberRole.add(role);
  }

  public void changeWallet(String wallet) {
    this.wallet = wallet;
  }
}
