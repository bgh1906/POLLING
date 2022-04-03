package com.polling.auth.dto.response;

import com.polling.member.entity.status.MemberRole;
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

}
