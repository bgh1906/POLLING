package com.polling.auth.controller;

import com.polling.aop.annotation.Trace;
import com.polling.auth.adapter.MemberAndDtoAdapter;
import com.polling.auth.dto.MemberDto;
import com.polling.auth.dto.request.AuthRequestDto;
import com.polling.auth.dto.request.LoginRequestDto;
import com.polling.auth.dto.request.ValidateMemberRequestDto;
import com.polling.auth.dto.response.LoginResponseDto;
import com.polling.auth.dto.response.ValidateMemberResponseDto;
import com.polling.auth.service.AuthService;
import com.polling.common.JwtTokenProvider;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.entity.status.MemberRole;
import com.polling.member.repository.MemberRepository;
import com.polling.security.service.RedisService;
import io.swagger.annotations.ApiOperation;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자 SNS 및 네이티브 로그인 관련 컨트롤러
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationRestController {

  private final JwtTokenProvider jwtTokenProvider;
  private final RedisService redisService;
  private final MemberRepository memberRepository;
  private final AuthService authService;

  @Trace
  @PostMapping
  @ApiOperation(value = "Native 로그인")
  public ResponseEntity<LoginResponseDto> authorize(@RequestBody LoginRequestDto loginDto,
      HttpServletResponse response) {
    Member member = memberRepository.findByEmail(loginDto.getEmail())
        .orElseThrow(() -> new CustomException(CustomErrorResult.USER_NOT_FOUND));
    if (!member.getPassword().equals(loginDto.getPassword())) {
      throw new CustomException(CustomErrorResult.USER_NOT_FOUND);
    }
    setTokenHeaderAndRedis(member, response);
    LoginResponseDto responseDto = new LoginResponseDto(member.getId(),
        findHighestRole(member.getMemberRole()), member.getNickname());
    return ResponseEntity.status(200).body(responseDto);
  }

  @Trace
  @PostMapping("/social")
  @ApiOperation(value = "OAuth 회원 가입/로그인")
  public ResponseEntity<LoginResponseDto> authorizeOAuth(@RequestBody AuthRequestDto requestDto,
      HttpServletResponse response) {
    Member member = authService.auth(requestDto);
    setTokenHeaderAndRedis(member, response);
    LoginResponseDto responseDto = new LoginResponseDto(member.getId(),
        findHighestRole(member.getMemberRole()), member.getNickname());
    return ResponseEntity.status(200).body(responseDto);
  }


  @Trace
  @PostMapping("/validate")
  @ApiOperation(value = "기존 카카오가입회원이면 jwt+member:true, 신입이면 member:false 반환")
  public ResponseEntity<ValidateMemberResponseDto> ValidateMember(
      @RequestBody ValidateMemberRequestDto requestDto, HttpServletResponse response) {
    ValidateMemberResponseDto responseDto = new ValidateMemberResponseDto();
    Member member = authService.validate(requestDto);
    if (member == null) {
      responseDto.setExistMember(false);
    } else {
      responseDto.setExistMember(true);
      responseDto.setField(findHighestRole(member.getMemberRole()), member.getNickname(),
          member.getId());
      setTokenHeaderAndRedis(member, response);
    }
    return ResponseEntity.status(200).body(responseDto);
  }

  @GetMapping("/logout")
  @ApiOperation(value = "로그아웃")
  public ResponseEntity<Void> logout(HttpServletRequest request) {
    redisService.delValues(request.getHeader("refreshToken"));
    return ResponseEntity.status(200).build();
  }

  public void setTokenHeaderAndRedis(Member member, HttpServletResponse response) {
    MemberDto memberDto = MemberAndDtoAdapter.entityToDto(member);
    String accessToken = jwtTokenProvider.createAccessToken(memberDto.getId().toString(),
        memberDto.getMemberRole());
    String refreshToken = jwtTokenProvider.createRefreshToken(memberDto.getId().toString(),
        memberDto.getMemberRole());
    jwtTokenProvider.setHeaderAccessToken(response, accessToken);
    jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

    // Redis 인메모리에 리프레시 토큰 저장
    redisService.setValues(refreshToken, memberDto.getId());
  }

  /**
   * 멤버의 최상위 권한을 찾는 로직 ADMIN > COMPNAY > USER
   */
  private MemberRole findHighestRole(Set<MemberRole> roles) {
    if (roles.contains(MemberRole.ROLE_ADMIN)) {
      return MemberRole.ROLE_ADMIN;
    } else if (roles.contains(MemberRole.ROLE_COMPANY)) {
      return MemberRole.ROLE_COMPANY;
    } else {
      return MemberRole.ROLE_USER;
    }
  }
}
