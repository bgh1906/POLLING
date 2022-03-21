package com.polling.api.service.member;

import com.polling.api.controller.exception.CustomErrorResult;
import com.polling.api.controller.exception.CustomException;
import com.polling.api.controller.member.dto.request.ChangePasswordRequestDto;
import com.polling.api.controller.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.api.controller.member.dto.response.FindMemberResponseDto;
import com.polling.core.entity.member.Member;
import com.polling.core.entity.member.status.MemberRole;
import com.polling.core.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

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
        //기본 ROLE_USER, 상황에 따라 ROLE_COMPANY 추가
        if(MemberRole.ROLE_COMPANY.equals(requestDto.getRole())){
            member.addRole(MemberRole.ROLE_COMPANY);
        }

        memberRepository.save(member);
    }

    public void checkDuplicateMemberEmail(String email){
        if(memberRepository.existsByEmail(email))
            throw new CustomException(CustomErrorResult.DUPLICATE_EMAIL);
    }

    public void checkDuplicateMemberNickname(String nickname){
        if(memberRepository.existsByNickname(nickname))
            throw new CustomException(CustomErrorResult.DUPLICATE_NICKNAME);
    }

    public FindMemberResponseDto findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));

        return new FindMemberResponseDto(member.getId(), member.getNickname(), member.getEmail());
    }

    @Transactional
    public void changePassword(Long id, ChangePasswordRequestDto requestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));
        member.changePassword(requestDto.getPassword());
    }

    @Transactional
    public void changeNickname(Long id, String nickname) {
        checkDuplicateMemberNickname(nickname);
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));
        member.changeNickname(nickname);
    }

    @Transactional
    public void changeRole(Long id, Set<MemberRole> memberRole) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));
        member.changeMemberRole(memberRole);
    }
}
