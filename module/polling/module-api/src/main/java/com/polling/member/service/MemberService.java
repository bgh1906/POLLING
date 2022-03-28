package com.polling.member.service;

import com.polling.aop.annotation.Trace;
import com.polling.entity.member.Member;
import com.polling.entity.member.status.MemberRole;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.dto.request.ChangePasswordRequestDto;
import com.polling.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.member.dto.response.FindMemberResponseDto;
import com.polling.repository.member.MemberRepository;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class
MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public void addMember(SaveNativeMemberRequestDto requestDto) {
    checkDuplicateMemberEmail(requestDto.getEmail());

    Member member = Member.builder()
        .nickname(requestDto.getNickname())
        .email(requestDto.getEmail())
        .password(requestDto.getPassword())
        .phoneNumber(requestDto.getPhoneNumber())
        .build();

    member.addRole(requestDto.getRole());

    memberRepository.save(member);
  }

  public void checkDuplicateMemberEmail(String email) {
    if (memberRepository.existsByEmail(email)) {
      throw new CustomException(CustomErrorResult.DUPLICATE_EMAIL);
    }
  }

  public void checkDuplicateMemberNickname(String nickname) {
    if (memberRepository.existsByNickname(nickname)) {
      throw new CustomException(CustomErrorResult.DUPLICATE_NICKNAME);
    }
  }

  public FindMemberResponseDto findMember(Long memberId) {
    Member member = getMember(memberId);

    return FindMemberResponseDto.of(member);
  }

  @Trace
  @Transactional
  public void changePassword(Long memberId, ChangePasswordRequestDto requestDto) {
    Member member = getMember(memberId);
    member.changePassword(requestDto.getPassword());
  }

  @Trace
  @Transactional
  public void changeNickname(Long memberId, String nickname) {
    checkDuplicateMemberNickname(nickname);
    Member member = getMember(memberId);
    member.changeNickname(nickname);
  }

  @Trace
  @Transactional
  public void changeRole(Long memberId, Set<MemberRole> memberRole) {
    Member member = getMember(memberId);
    member.changeMemberRole(memberRole);
  }

  @Trace
  @Transactional
  public void addAdminRole(Long memberId) {
    Member member = getMember(memberId);
    Set<MemberRole> memberRoles = new HashSet<>();
    memberRoles.add(MemberRole.ROLE_ADMIN);
    memberRoles.add(MemberRole.ROLE_USER);
    member.changeMemberRole(memberRoles);
  }

  public Member getMember(Long memberId) {
    return memberRepository.findById(memberId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.USER_NOT_FOUND));
  }
}
