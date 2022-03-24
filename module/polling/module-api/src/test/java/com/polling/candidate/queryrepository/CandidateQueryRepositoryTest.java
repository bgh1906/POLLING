package com.polling.candidate.queryrepository;

import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.poll.Poll;
import com.polling.queryrepository.CandidateQueryRepository;
import com.polling.repository.poll.PollRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class CandidateQueryRepositoryTest {

    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private CandidateQueryRepository candidateQueryRepository;

    @Test
    public void 투표랭킹조회_득표순으로() throws Exception{
        //given
        Poll savedPoll = pollRepository.save(createPoll("testTitle"));
        Candidate candidate1 = Candidate.builder().name("TestA").build();
        candidate1.addVoteTotal(1);
        Candidate candidate2 = Candidate.builder().name("TestA").build();
        savedPoll.addCandidate(candidate1);
        savedPoll.addCandidate(candidate2);

        //when
        List<FindCandidateResponseDto> responseDtos = candidateQueryRepository.findAllByPollIdOrderByVotesTotal(savedPoll.getId());

        //then
        assertThat(responseDtos.get(0).getVotesTotalCount()).isEqualTo(1);
        assertThat(responseDtos.get(1).getVotesTotalCount()).isEqualTo(0);
    }

    private Poll createPoll(String title){
        return Poll.builder()
                .title(title)
                .content("testContent")
                .build();
    }
}
