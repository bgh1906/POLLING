package com.polling.auth.dto.response;

import com.polling.member.entity.Member;
import com.polling.member.entity.status.MemberRole;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로그인 후 클라이언트에게 전달되는 반환 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoginResponseDto {

  private Long id;
  private MemberRole role;
  private String nickname;
  private String wallet;

  public static LoginResponseDto of(Member member) {
    return new LoginResponseDto(member.getId(),
        findHighestRole(member.getMemberRole()),
        member.getNickname(),
        member.getWallet());
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
