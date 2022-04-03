package com.polling.poll.candidate.repository;

import com.polling.entity.candidate.CandidateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateHistoryRepository extends JpaRepository<CandidateHistory, Long> {

}
