package com.polling.auth.adapter;

import com.polling.auth.dto.MemberDto;
import com.polling.entity.member.Member;

public class MemberAndDtoAdapter {
    public static MemberDto entityToDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .phoneNumber(member.getPhoneNumber())
                .memberRole(member.getMemberRole())
                .createDate(member.getCreatedDate())
                .build();
    }
}
