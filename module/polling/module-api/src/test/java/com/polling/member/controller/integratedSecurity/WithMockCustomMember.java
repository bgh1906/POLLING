package com.polling.member.controller.integratedSecurity;

import com.polling.entity.member.status.MemberRole;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * todo : spring security 단위 테스트에 적용
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithUserDetailsSecurityContextFactory.class)
public @interface WithMockCustomMember {
    String id() default "1";
    MemberRole role() default  MemberRole.ROLE_USER;
}
