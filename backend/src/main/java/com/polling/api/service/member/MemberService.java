package com.polling.api.service.member;

import com.polling.common.security.adapter.MemberAndDtoAdapter;
import com.polling.common.security.adapter.MemberAndUserAdapter;
import com.polling.core.entity.member.Member;
import com.polling.core.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found in the database"));
        return MemberAndUserAdapter.from(MemberAndDtoAdapter.entityToDto(findMember));
    }
}
