package com.polling.repository.poll;

import com.polling.entity.poll.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long> {
    Optional<Poll> findByTitle(String title);
}
