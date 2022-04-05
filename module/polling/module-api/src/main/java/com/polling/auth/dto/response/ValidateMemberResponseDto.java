package com.polling.auth.dto.response;

import com.polling.member.entity.Member;
import com.polling.member.entity.status.MemberRole;
import java.util.Set;
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

  private Long id;
  private boolean existMember = false;
  private MemberRole role;
  private String nickname;

  public static ValidateMemberResponseDto of(Member member) {
    return new ValidateMemberResponseDto(member.getId(),
        true,
        findHighestRole(member.getMemberRole()),
        member.getNickname());
  }

  /**
   * 멤버의 최상위 권한을 찾는 로직 ADMIN > COMPNAY > USER
   */
  private static MemberRole findHighestRole(Set<MemberRole> roles) {
    if (roles.contains(MemberRole.ROLE_ADMIN)) {
      return MemberRole.ROLE_ADMIN;
    } else if (roles.contains(MemberRole.ROLE_COMPANY)) {
      return MemberRole.ROLE_COMPANY;
    } else {
      return MemberRole.ROLE_USER;
    }
  }
}
