package com.polling.auth.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for storing a user's credentials.
 */
@Data
public class LoginRequestDto {

  @NotNull
  private String email;

  @NotNull
  private String password;
}
