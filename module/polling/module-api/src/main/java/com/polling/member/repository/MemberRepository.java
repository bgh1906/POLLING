package com.polling.member.repository;


import com.polling.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String email);

  Optional<Member> findByNickname(String nickname);

  Optional<Member> findByOauthId(String id);

  Boolean existsByNickname(String nickname);

  Boolean existsByEmail(String email);

  Boolean existsByPhoneNumber(String phoneNumber);
}