package com.polling.token.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import com.polling.poll.candidate.repository.CandidateRepository;
import com.polling.token.dto.response.FindTokenUsageHistoryResponseDto;
import com.polling.token.entity.TokenUsageHistory;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@Slf4j
public class TokenUsageHistoryQueryRepositoryTest {

  @Autowired
  private TokenUsageHistoryRepository tokenUsageHistoryRepository;
  @Autowired
  private TokenUsageHistoryQueryRepository historyQueryRepository;
  @Autowired
  private CandidateRepository candidateRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private EntityManager em;

  @Test
  public void 특정후보자의시크릿이미지를구매한내역조회() throws Exception {
    //given
    Member savedMember = memberRepository.save(Member.builder().build());
    Candidate candidate = Candidate.builder().build();
    candidate.addGallery(new CandidateGallery("image1"));
    candidate.addGallery(new CandidateGallery("image2"));
    candidate.addGallery(new CandidateGallery("image3"));
    Candidate savedCandidate = candidateRepository.save(candidate);
    em.flush();
    em.clear();

    tokenUsageHistoryRepository.save(TokenUsageHistory.builder()
        .candidate(savedCandidate)
        .member(savedMember)
        .gallery(savedCandidate.getGalleries().get(0))
        .build());

    tokenUsageHistoryRepository.save(TokenUsageHistory.builder()
        .candidate(savedCandidate)
        .member(savedMember)
        .gallery(savedCandidate.getGalleries().get(2))
        .build());

    tokenUsageHistoryRepository.save(TokenUsageHistory.builder()
        .member(savedMember)
        .build());

    //when
    List<FindTokenUsageHistoryResponseDto> responseDtos = historyQueryRepository
        .findSecretByMemberIdAndCandidateId(savedMember.getId(), savedCandidate.getId());

    //then
    assertThat(responseDtos.size()).isEqualTo(2);
    assertThat(responseDtos.get(0).getImagePath3()).isEqualTo("image1");
    assertThat(responseDtos.get(1).getImagePath3()).isEqualTo("image3");
  }
}
