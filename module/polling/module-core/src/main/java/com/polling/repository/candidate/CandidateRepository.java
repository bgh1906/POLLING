package com.polling.repository.candidate;

import com.polling.entity.candidate.Candidate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

  Optional<Candidate> findByName(String name);
}
