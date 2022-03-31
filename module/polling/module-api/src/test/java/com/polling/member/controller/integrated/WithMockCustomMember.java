package com.polling.member.controller.integrated;

import com.polling.entity.member.status.MemberRole;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithSecurityContext;

/**
 * todo : spring security 단위 테스트에 적용
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithUserDetailsSecurityContextFactory.class)
public @interface WithMockCustomMember {

  String id() default "1";

  MemberRole role() default MemberRole.ROLE_USER;
}
