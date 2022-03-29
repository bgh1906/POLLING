package com.polling.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {

  private Long id;

  private String nickname;

  public static LoginResponseDto of(Long id, String nickname) {
    return new LoginResponseDto(id, nickname);
  }
}
