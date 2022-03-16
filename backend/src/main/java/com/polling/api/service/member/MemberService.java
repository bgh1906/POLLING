package com.polling.api.service.member;

import com.polling.api.controller.exception.CustomException;
import com.polling.api.controller.exception.ErrorCode;
import com.polling.api.controller.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.api.controller.member.dto.request.UpdateMemberRequestDto;
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
    public void save(SaveNativeMemberRequestDto requestDto) {
        //todo 유저 있는지 없는지 체크

        Member member = Member.builder()
                .nickname(requestDto.getName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();

        memberRepository.save(member);
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
    public void updateUser(Long id, UpdateMemberRequestDto requestDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        member.nameUpdate(requestDto.getName());
        member.passwordUpdate(requestDto.getPassword());
    }

    @Transactional
    public void updateRole(Long id, Set<MemberRole> memberRole) {
        Member member = memberRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        member.changeMemberRole(memberRole);
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found in the database"));
        return MemberAndUserAdapter.from(MemberAndDtoAdapter.entityToDto(findMember));
    }
}
