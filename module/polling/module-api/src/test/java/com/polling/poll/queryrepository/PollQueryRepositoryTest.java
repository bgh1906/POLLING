package com.polling.poll.queryrepository;

import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.config.JpaConfig;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.poll.Poll;
import com.polling.queryrepository.PollQueryRepository;
import com.polling.repository.poll.PollRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class PollQueryRepositoryTest {
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private PollQueryRepository pollQueryRepository;
    
    @Test
    public void 투표랭킹조회_득표순으로() throws Exception{
        //given
        Poll savedPoll = pollRepository.save(Poll.builder()
                .title("testTitle")
                .content("testContent")
                .build());
        Candidate candidate1 = Candidate.builder().name("TestA").build();
        candidate1.addVoteTotal(1);
        Candidate candidate2 = Candidate.builder().name("TestA").build();
        savedPoll.addCandidate(candidate1);
        savedPoll.addCandidate(candidate2);

        //when
        List<FindCandidateResponseDto> responseDtos = pollQueryRepository.findCandidatesSortByVoteTotal(savedPoll.getId());

        //then
        assertThat(responseDtos.get(0).getVotesTotalCount()).isEqualTo(1);
        assertThat(responseDtos.get(1).getVotesTotalCount()).isEqualTo(0);
    }
}
