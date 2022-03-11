package com.ssafy.core.repository.candidate;

import com.ssafy.core.entity.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
