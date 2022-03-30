package com.polling.poll.queryrepository.candidate;

import static org.assertj.core.api.Assertions.assertThat;

import com.polling.entity.candidate.Candidate;
import com.polling.entity.candidate.CandidateGallery;
import com.polling.entity.poll.Poll;
import com.polling.queryrepository.CandidateQueryRepository;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.poll.PollRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class CandidateQueryRepositoryTest {

  @Autowired
  private PollRepository pollRepository;
  @Autowired
  private CandidateRepository candidateRepository;
  @Autowired
  private CandidateQueryRepository candidateQueryRepository;


  @Test
  public void 후보자조회_모든정보() throws Exception {
    //given
    Poll savedPoll = createPoll("test");
    Candidate candidate1 = createCandidate(1);
    Candidate candidate2 = createCandidate(2);
    savedPoll.addCandidate(candidate1);
    savedPoll.addCandidate(candidate2);
    pollRepository.save(savedPoll);

    //when
    List<Candidate> candidates = candidateQueryRepository.findAllByPollId(savedPoll.getId());

    //then
    assertThat(candidates.size()).isEqualTo(2);
    assertThat(candidates.get(0).getGalleries().get(0).getImagePath()).isEqualTo("123");
    assertThat(candidates.get(0).getGalleries().get(1).getImagePath()).isEqualTo("345");
    assertThat(candidates.get(0).getGalleries().get(2).getImagePath()).isEqualTo("567");
    assertThat(candidates.get(1).getName()).isEqualTo("Test2");
  }

  @Test
  public void 후보자전체삭제_투표단위() throws Exception {
    //given
    Poll poll = createPoll("test");
    Candidate candidate1 = createCandidate(1);
    Candidate candidate2 = createCandidate(2);
    poll.addCandidate(candidate1);
    poll.addCandidate(candidate2);
    Poll savedPoll = pollRepository.save(poll);
    candidateQueryRepository.deleteGalleriesByCandidateId(candidate1.getId());
    candidateQueryRepository.deleteGalleriesByCandidateId(candidate2.getId());

    //when
    candidateQueryRepository.deleteByPollId(savedPoll.getId());

    //then
    assertThat(candidateRepository.count()).isEqualTo(0);
  }


  private Poll createPoll(String title) {
    return Poll.builder()
        .title(title)
        .content("testContent")
        .build();
  }

  private Candidate createCandidate(Integer index) {
    Candidate candidate = Candidate
        .builder()
        .contractIndex(index)
        .name("Test" + index)
        .thumbnail("thumbNail")
        .build();
    candidate.addGallery(new CandidateGallery("123"));
    candidate.addGallery(new CandidateGallery("345"));
    candidate.addGallery(new CandidateGallery("567"));

    return candidate;
  }
}
