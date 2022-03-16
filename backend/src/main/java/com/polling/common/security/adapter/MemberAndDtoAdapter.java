package com.polling.common.security.adapter;

import com.polling.common.security.dto.MemberDto;
import com.polling.core.entity.member.Member;

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
