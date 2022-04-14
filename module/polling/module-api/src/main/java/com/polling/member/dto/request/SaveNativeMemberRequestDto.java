package com.polling.member.dto.request;

import com.polling.member.entity.Member;
import com.polling.member.entity.status.MemberRole;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Native 회원 가입 요청 DTO
 */
@Getter
@NoArgsConstructor
public class SaveNativeMemberRequestDto {

  @NotNull
  private String nickname;
  @NotNull
  private String wallet;
  @NotNull
  private String email;
  @NotNull
  private String password;
  @NotNull
  private String phoneNumber;
  private MemberRole role;

  @Builder
  public SaveNativeMemberRequestDto(String nickname, String wallet, String email,
      String password, String phoneNumber, MemberRole role) {
    this.nickname = nickname;
    this.wallet = wallet;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.role = role;
  }

  public Member toEntity() {
    Member member = Member.builder()
        .wallet(wallet)
        .nickname(nickname)
        .email(email)
        .password(password)
        .phoneNumber(phoneNumber)
        .build();
    member.addRole(role);
    return member;
  }
}
