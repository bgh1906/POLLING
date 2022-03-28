package com.polling.repository.member;

import com.polling.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByOauthId(String id);
    Boolean existsByNickname(String nickname);
    Boolean existsByEmail(String email);
}