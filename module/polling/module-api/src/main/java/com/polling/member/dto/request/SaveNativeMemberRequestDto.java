package com.polling.member.dto.request;

import com.polling.member.entity.Member;
import com.polling.member.entity.status.MemberRole;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Native 회원 가입 요청 DTO
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveNativeMemberRequestDto {

  @NotNull
  private String wallet;
  @NotNull
  private String nickname;
  @NotNull
  private String email;
  @NotNull
  private String password;
  @NotNull
  private String phoneNumber;

  private MemberRole role;

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
