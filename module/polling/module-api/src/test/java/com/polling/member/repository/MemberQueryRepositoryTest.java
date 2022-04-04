package com.polling.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.polling.member.dto.response.FindMemberResponseDto;
import com.polling.member.entity.Member;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class MemberQueryRepositoryTest {

  @Autowired
  private MemberQueryRepository queryRepository;
  @Autowired
  private MemberRepository memberRepository;

  @Test
  public void 모든유저조회() throws Exception {
    //given
    memberRepository.deleteAll();
    memberRepository.save(Member.builder().build());
    memberRepository.save(Member.builder().build());
    memberRepository.save(Member.builder().build());
    memberRepository.save(Member.builder().build());
    memberRepository.save(Member.builder().build());

    //when
    List<FindMemberResponseDto> result = queryRepository.findAll(0, 10);

    //then
    assertThat(result.size()).isEqualTo(5);
  }

}
