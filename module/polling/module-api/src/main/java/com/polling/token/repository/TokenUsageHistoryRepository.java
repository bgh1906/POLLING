package com.polling.token.repository;

import com.polling.token.entity.TokenUsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenUsageHistoryRepository extends JpaRepository<TokenUsageHistory, Long> {

}