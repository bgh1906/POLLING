package com.polling.token.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.polling.config.jpa.JpaConfig;
import com.polling.token.entity.TokenUsageHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(JpaConfig.class)
@DataJpaTest
public class TokenUsageHistoryRepositoryTest {

  @Autowired
  TokenUsageHistoryRepository tokenUsageHistoryRepository;

  @Test
  public void 토큰사용내역저장() throws Exception {
    //given

    //when
    tokenUsageHistoryRepository.save(TokenUsageHistory.builder().build());

    //then
    assertThat(tokenUsageHistoryRepository.count()).isEqualTo(1);
  }
}
