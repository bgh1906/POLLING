package com.polling.repository.candidate;

import com.polling.config.jpa.JpaConfig;
import com.polling.entity.candidate.CandidateHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(JpaConfig.class)
@DataJpaTest
public class CandidateHistoryRepositoryTest {

  @Autowired
  private CandidateHistoryRepository candidateHistoryRepository;

  @Test
  public void candidateHistoryIsNotNull() throws Exception {
    assertThat(candidateHistoryRepository).isNotNull();
  }

  @Test
  public void 후보자에투표내역저장() throws Exception {
    //given
    CandidateHistory history = CandidateHistory.builder()
        .member(null)
        .candidate(null)
        .voteCount(1)
        .transactionId("transactionId")
        .build();

    //when
    CandidateHistory save = candidateHistoryRepository.save(history);

    //then
    assertThat(save.getId()).isNotNull();
  }
}
