package com.polling.auth.adapter;

import com.polling.auth.dto.MemberDto;
import com.polling.entity.member.status.MemberRole;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * MemberDto와 UserDeatils를 연결하는 Adapter
 */
@Getter
public class MemberAndUserAdapter extends User {

  private final MemberDto memberDto;

  private MemberAndUserAdapter(MemberDto memberDto) {
    super(memberDto.getEmail(), memberDto.getPassword(),
        authorities(memberDto.getMemberRole()));
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
