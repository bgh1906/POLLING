package com.polling.poll.candidate.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import com.polling.poll.poll.entity.Poll;
import com.polling.poll.poll.repository.PollRepository;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@SpringBootTest
public class CandidateQueryRepositoryTest {

  @Autowired
  private PollRepository pollRepository;
  @Autowired
  private CandidateRepository candidateRepository;
  @Autowired
  private CandidateQueryRepository candidateQueryRepository;
  @Autowired
  private EntityManager em;


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
    List<Candidate> candidates = savedPoll.getCandidates();

    //then
    assertThat(candidates.size()).isEqualTo(2);
    assertThat(candidates.get(0).getGalleries().get(0).getImagePath()).isEqualTo("123");
    assertThat(candidates.get(0).getGalleries().get(1).getImagePath()).isEqualTo("345");
    assertThat(candidates.get(0).getGalleries().get(2).getImagePath()).isEqualTo("567");
    assertThat(candidates.get(1).getName()).isEqualTo("Test2");
  }

  @Test
  public void 후보자삭제_단일() throws Exception {
    //given
    Poll savedPoll = pollRepository.save(Poll.builder().build());
    savedPoll.addCandidate(createCandidate(1));
    em.flush();
    em.clear();

    System.out.println(savedPoll.getCandidates().get(0).getId());

    //when
    candidateQueryRepository.deleteGalleryById(savedPoll.getCandidates().get(0).getId());
    candidateRepository.deleteById(savedPoll.getCandidates().get(0).getId());

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
