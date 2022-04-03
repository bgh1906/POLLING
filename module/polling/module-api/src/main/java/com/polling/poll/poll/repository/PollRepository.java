package com.polling.poll.poll.repository;

import com.polling.poll.poll.entity.Poll;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {

  Optional<Poll> findByTitle(String title);
}
