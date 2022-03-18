package com.polling.api.service.member;

import com.polling.api.controller.exception.CustomException;
import com.polling.api.controller.exception.ErrorCode;
import com.polling.api.controller.member.dto.request.ChangeNicknameRequestDto;
import com.polling.api.controller.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.api.controller.member.dto.request.ChangePasswordRequestDto;
import com.polling.api.controller.member.dto.response.FindMemberResponseDto;
import com.polling.common.security.adapter.MemberAndDtoAdapter;
import com.polling.common.security.adapter.MemberAndUserAdapter;
import com.polling.core.entity.member.Member;
import com.polling.core.entity.member.status.MemberRole;
import com.polling.core.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public long save(SaveNativeMemberRequestDto requestDto) {
        Member member = Member.builder()
                .nickname(requestDto.getNickname())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();

        memberRepository.save(member);
        return member.getId();
    }

    public void checkDuplicateMember(String email){
        if(memberRepository.existsByEmail(email))
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
    }

    public void checkDuplicateMemberNickname(String nickname){
        if(memberRepository.existsByNickname(nickname))
            throw new CustomException(ErrorCode.DUPLICATE_NAME);
    }

    public FindMemberResponseDto findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

        return new FindMemberResponseDto(member.getId(), member.getNickname(), member.getEmail());
    }

    @Transactional
    public void changePassword(Long id, ChangePasswordRequestDto requestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        member.changePassword(requestDto.getPassword());
    }

    @Transactional
    public void changeNickname(Long id, ChangeNicknameRequestDto requestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        member.changeNickname(requestDto.getNickname());
    }

    @Transactional
    public void updateRole(Long id, Set<MemberRole> memberRole) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        member.changeMemberRole(memberRole);
    }

    @Override
    public UserDetails loadUserByUsername(final String id) {
        Member findMember = memberRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " was not found in the database"));
        return MemberAndUserAdapter.from(MemberAndDtoAdapter.entityToDto(findMember));
    }
}
