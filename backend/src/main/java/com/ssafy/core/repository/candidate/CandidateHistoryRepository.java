package com.ssafy.core.repository.candidate;

import com.ssafy.core.entity.candidate.CandidateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateHistoryRepository extends JpaRepository<CandidateHistory, Long> {
}
