package com.polling.common.security.adapter;

import com.polling.common.security.dto.MemberDto;
import com.polling.core.entity.member.status.MemberRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class MemberAndUserAdapter extends User {
    private final MemberDto memberDto;

    private MemberAndUserAdapter(MemberDto memberDto) {
        super(memberDto.getEmail(), memberDto.getPassword(), authorities(memberDto.getMemberRole()));
        this.memberDto = memberDto;
    }

    public static MemberAndUserAdapter from(MemberDto memberDto) {
        return new MemberAndUserAdapter(memberDto);
    }

    private static Collection<? extends GrantedAuthority> authorities(Set<MemberRole> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.name()))
                .collect(Collectors.toSet());
    }

}
