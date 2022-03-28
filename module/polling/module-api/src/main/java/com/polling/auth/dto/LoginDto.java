package com.polling.auth.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for storing a user's credentials.
 */
@Data
public class LoginDto {

  @NotNull
  private String email;

  @NotNull
  private String password;
}
