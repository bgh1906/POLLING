package com.polling.member.controller.integratedSecurity;

import com.polling.common.security.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.test.context.support.WithUserDetails;

@RequiredArgsConstructor
final class WithUserDetailsSecurityContextFactory
        implements WithSecurityContextFactory<WithUserDetails> {

    private final MemberDetailsService userDetailsService;

    public SecurityContext createSecurityContext(WithUserDetails withUser) {
        String username = withUser.value();
        UserDetails principal = userDetailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }
}