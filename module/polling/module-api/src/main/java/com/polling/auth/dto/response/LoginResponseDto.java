package com.polling.auth.dto.response;

import com.polling.entity.member.status.MemberRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoginResponseDto {

  private Long id;

  private MemberRole role;

  private String nickname;


}
