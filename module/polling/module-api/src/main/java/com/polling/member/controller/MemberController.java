package com.polling.member.controller;

import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.auth.CurrentUser;
import com.polling.auth.dto.MemberDto;
import com.polling.member.dto.request.ChangePasswordRequestDto;
import com.polling.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.member.dto.response.FindMemberResponseDto;
import com.polling.member.repository.MemberRepository;
import com.polling.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  private final MemberRepository memberRepository;

  @Retry
  @PostMapping
  @ApiOperation(value = "회원 가입")
  public ResponseEntity<Void> save(@RequestBody SaveNativeMemberRequestDto requestDto) {
    memberService.join(requestDto);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @DeleteMapping
  @ApiOperation(value = "회원 탈퇴")
  public ResponseEntity<Void> delete(@CurrentUser MemberDto memberDto) {
    memberRepository.deleteById(memberDto.getId());
    return ResponseEntity.status(200).build();
  }

  @Trace
  @GetMapping
  @ApiOperation(value = "회원 정보 조회")
  public ResponseEntity<FindMemberResponseDto> getMember(@CurrentUser MemberDto memberDto) {
    FindMemberResponseDto responseDto = memberService.findMember(memberDto.getId());
    return ResponseEntity.status(200).body(responseDto);
  }

  @GetMapping("/nickname/{nickname}")
  @ApiOperation(value = "닉네임 중복체크")
  public ResponseEntity<Void> checkNickname(@PathVariable("nickname") String nickname) {
    memberService.checkDuplicateMemberNickname(nickname);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @PatchMapping("/nickname/{nickname}")
  @ApiOperation(value = "닉네임 수정")
  public ResponseEntity<Void> changeNickname(@CurrentUser MemberDto memberDto,
      @PathVariable String nickname) {
    memberService.changeNickname(memberDto.getId(), nickname);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @PatchMapping("/password/{id}")
  @ApiOperation(value = "패스워드 수정")
  public ResponseEntity<Void> changePassword(@CurrentUser MemberDto memberDto,
      @RequestBody ChangePasswordRequestDto requestDto) {
    memberService.changePassword(memberDto.getId(), requestDto);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @PatchMapping("/role/admin/{id}")
  @ApiOperation(value = "임시용 멤버에 어드민 권한 추가")
  public ResponseEntity<Void> changeRole(@PathVariable Long id) {
    memberService.addAdminRole(id);
    return ResponseEntity.status(200).build();
  }

}
