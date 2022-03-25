package com.polling.auth.controller;

import com.polling.auth.JwtTokenProvider;
import com.polling.auth.adapter.MemberAndDtoAdapter;
import com.polling.auth.dto.AuthDto;
import com.polling.auth.dto.LoginDto;
import com.polling.auth.dto.MemberDto;
import com.polling.auth.service.AuthService;
import com.polling.entity.member.Member;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.repository.member.MemberRepository;
import com.polling.security.service.RedisService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller to authenticate users.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationRestController {

   private final JwtTokenProvider jwtTokenProvider;
   private final RedisService redisService;
   private final MemberRepository memberRepository;
   private final AuthService authService;

   @PostMapping
   @ApiOperation(value = "Native 로그인")
   public ResponseEntity<Void> authorize(@RequestBody LoginDto loginDto, HttpServletResponse response) {
      Member member = memberRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new CustomException(CustomErrorResult.USER_NOT_FOUND));
      if(!member.getPassword().equals(loginDto.getPassword()))
         throw new CustomException(CustomErrorResult.USER_NOT_FOUND);
      setTokenHeaderAndRedis(member, response);
      return ResponseEntity.status(200).build();
   }

   @PostMapping("/social")
   @ApiOperation(value = "OAuth 회원 가입/로그인")
   public ResponseEntity<Void> authorizeOAuth(@RequestBody AuthDto requestDto, HttpServletResponse response) {
      Member member = authService.auth(requestDto);
      setTokenHeaderAndRedis(member, response);
      return ResponseEntity.status(200).build();
   }


   @GetMapping("/logout")
   public ResponseEntity<Void> logout(HttpServletRequest request) {
      redisService.delValues(request.getHeader("refreshToken"));
      return ResponseEntity.status(200).build();
   }

   public void setTokenHeaderAndRedis(Member member, HttpServletResponse response){
      MemberDto memberDto = MemberAndDtoAdapter.entityToDto(member);
      String accessToken = jwtTokenProvider.createAccessToken(memberDto.getId().toString(), memberDto.getMemberRole());
      String refreshToken = jwtTokenProvider.createRefreshToken(memberDto.getId().toString(), memberDto.getMemberRole());
      jwtTokenProvider.setHeaderAccessToken(response, accessToken);
      jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

      // Redis 인메모리에 리프레시 토큰 저장
      redisService.setValues(refreshToken, memberDto.getId());
   }
}
