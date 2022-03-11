package com.ssafy.core.repository.vote;

import com.ssafy.core.entity.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
