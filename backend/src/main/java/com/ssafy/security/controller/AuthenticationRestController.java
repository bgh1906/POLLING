package com.ssafy.backend.security.controller;

import com.ssafy.backend.security.dto.LoginDto;
import com.ssafy.backend.security.jwt.JwtFilter;
import com.ssafy.backend.security.jwt.TokenProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationRestController {

   private final TokenProvider tokenProvider;

   private final AuthenticationManagerBuilder authenticationManagerBuilder;

   @PostMapping("/auth")
   public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginDto loginDto) {

      UsernamePasswordAuthenticationToken authenticationToken =
         new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

      Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      boolean rememberMe = loginDto.getRememberMe() != null && loginDto.getRememberMe();
      String token = tokenProvider.createToken(authentication, rememberMe);

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

      return ResponseEntity.status(200).body(new JWTToken(token, 200, "Success"));
   }

   /**
    * Object to return as body in JWT Authentication.
    */
   @Getter
   static class JWTToken{

      private int status;
      private String message;
      private String idToken;

      JWTToken(String idToken, int status, String message) {
         this.status = status;
         this.message = message;
         this.idToken = idToken;
      }
   }
}
