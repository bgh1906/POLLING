package com.polling.poll.poll.repository;

import java.util.Optional;

import com.polling.poll.poll.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {

  Optional<Poll> findByTitle(String title);
}
