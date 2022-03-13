package com.ssafy.common.security.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * DTO for storing a user's credentials.
 */
@Data
public class LoginDto {

   @NotNull
   private String email;

   @NotNull
   private String password;

   private Boolean rememberMe;

   @Override
   public String toString() {
      return "LoginVM{" +
         "username='" + email + '\'' +
         ", rememberMe=" + rememberMe +
         '}';
   }
}
