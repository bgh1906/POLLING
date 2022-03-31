package com.polling.auth.dto.response;

import com.polling.entity.member.status.MemberRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auth 회원 검증 후 유저 정보 반환 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateMemberResponseDto {

  private boolean existMember;
  private MemberRole role;
  private String nickname;
  private Long id;

  public void setField(MemberRole role, String nickname, Long id) {
    this.role = role;
    this.nickname = nickname;
    this.id = id;
  }
}
