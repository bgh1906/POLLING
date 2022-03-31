package com.polling.member.dto.request;

import com.polling.entity.member.Member;
import com.polling.entity.member.status.MemberRole;
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

  public Member toEntity() {
    Member member = Member.builder()
        .nickname(nickname)
        .wallet(wallet)
        .email(email)
        .password(password)
        .phoneNumber(phoneNumber)
        .build();
    member.addRole(role);
    return member;
  }
}
