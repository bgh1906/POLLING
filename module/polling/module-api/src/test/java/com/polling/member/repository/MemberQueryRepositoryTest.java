package com.polling.member.repository;

import com.polling.member.dto.response.FindMemberResponseDto;
import com.polling.member.entity.Member;
import com.polling.poll.candidate.dto.response.FindCandidateHistoryResponseDto;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import com.polling.poll.candidate.entity.CandidateHistory;
import com.polling.poll.candidate.repository.CandidateHistoryQueryRepository;
import com.polling.poll.candidate.repository.CandidateHistoryRepository;
import com.polling.poll.candidate.repository.CandidateRepository;
import com.polling.poll.poll.entity.Poll;
import com.polling.poll.poll.repository.PollRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
