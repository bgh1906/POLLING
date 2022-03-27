package com.polling.poll.queryrepository.candidate;

import com.polling.poll.dto.candidate.response.FindAnonymousCandidateResponseDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.poll.Poll;
import com.polling.queryrepository.CandidateQueryRepository;
import com.polling.repository.poll.PollRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<FindAnonymousCandidateResponseDto> responseDtos = candidateQueryRepository.findAllSimpleByPollIdOrderByVotesTotal(savedPoll.getId());

        //then
        assertThat(responseDtos.get(0).getVotesTotalCount()).isEqualTo(1);
        assertThat(responseDtos.get(1).getVotesTotalCount()).isEqualTo(0);
    }

    @Test
    public void 투표조회_모든정보() throws Exception{
        //given
        Poll savedPoll = pollRepository.save(createPoll("testTitle"));
        List<String> imagePaths = new ArrayList<>();
        imagePaths.add("123");
        imagePaths.add("345");
        imagePaths.add("567");
        Candidate candidate1 = Candidate
                .builder()
                .name("TestA")
                .thumbnail("thumbNail")
                .imagePaths(imagePaths)
                .build();
        Candidate candidate2 = Candidate.builder().name("TestA").build();
        savedPoll.addCandidate(candidate1);
        savedPoll.addCandidate(candidate2);

        //when
        List<Candidate> candidates = candidateQueryRepository.findAllByPollId(savedPoll.getId());

        //then
        assertThat(candidates.size()).isEqualTo(2);
        assertThat(candidates.get(0).getImagePaths().get(0)).isEqualTo("123");
        assertThat(candidates.get(0).getImagePaths().get(1)).isEqualTo("345");
        assertThat(candidates.get(0).getImagePaths().get(2)).isEqualTo("567");
        assertThat(candidates.get(1).getName()).isEqualTo("TestA");
    }


    private Poll createPoll(String title){
        return Poll.builder()
                .title(title)
                .content("testContent")
                .build();
    }
}
