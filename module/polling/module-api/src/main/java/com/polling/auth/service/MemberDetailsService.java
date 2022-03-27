package com.polling.auth.service;

import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.auth.adapter.MemberAndDtoAdapter;
import com.polling.auth.adapter.MemberAndUserAdapter;
import com.polling.entity.member.Member;
import com.polling.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Retry
    @Trace
    @Override
    public UserDetails loadUserByUsername(final String id) {
        Member findMember = memberRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " was not found in the database"));
        return MemberAndUserAdapter.from(MemberAndDtoAdapter.entityToDto(findMember));
    }
}
