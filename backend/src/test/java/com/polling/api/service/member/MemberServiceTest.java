package com.polling.api.service.member;

import com.polling.api.controller.exception.CustomException;
import com.polling.api.controller.exception.ErrorCode;
import com.polling.core.entity.member.Member;
import com.polling.core.entity.member.status.MemberRole;
import com.polling.core.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void role_change() throws Exception{
        //given
        memberRepository.save(Member.builder()
                .email("test@test.com")
                .nickname("nick")
                .password("1234")
                .build());

        //when
        Member member = memberRepository.findByEmail("test@test.com").orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Set<MemberRole> memberRoleSet = new HashSet<>();
        memberRoleSet.add(MemberRole.ROLE_USER);
        memberRoleSet.add(MemberRole.ROLE_COMPANY);
        member.changeMemberRole(memberRoleSet);
        em.flush();
        em.clear();

        //then

    }
}
