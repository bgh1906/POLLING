package com.polling.auth.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Native 로그인 요청 DTO
 */
@Data
public class LoginRequestDto {

  @NotNull
  private String email;

  @NotNull
  private String password;
}
