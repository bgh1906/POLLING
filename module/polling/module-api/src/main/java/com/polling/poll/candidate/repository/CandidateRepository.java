package com.polling.poll.candidate.repository;

import com.polling.poll.candidate.entity.Candidate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

  Optional<Candidate> findByName(String name);
}
