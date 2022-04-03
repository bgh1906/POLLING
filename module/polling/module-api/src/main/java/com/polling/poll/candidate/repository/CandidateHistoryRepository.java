package com.polling.poll.candidate.repository;

import com.polling.poll.candidate.entity.CandidateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateHistoryRepository extends JpaRepository<CandidateHistory, Long> {

}
