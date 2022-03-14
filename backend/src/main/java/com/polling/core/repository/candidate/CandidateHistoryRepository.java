package com.polling.core.repository.candidate;

import com.polling.core.entity.candidate.CandidateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateHistoryRepository extends JpaRepository<CandidateHistory, Long> {
}
