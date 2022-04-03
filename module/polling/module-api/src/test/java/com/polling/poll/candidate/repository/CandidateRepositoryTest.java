package com.polling.poll.candidate.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.polling.config.jpa.JpaConfig;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(JpaConfig.class)
@DataJpaTest
public class CandidateRepositoryTest {

  @Autowired
  private CandidateRepository candidateRepository;

  @Test
  public void candidateRepositoryIsNotNull() throws Exception {
    assertThat(candidateRepository).isNotNull();
  }

  @Test
  public void 후보자생성_이미지없음() throws Exception {
    //given

    //when
    Long saveCandidateId = candidateRepository.save(createCandidate(1)).getId();

    //then
    Candidate candidate = candidateRepository.findById(saveCandidateId).orElseThrow();
    assertThat(candidate.getId()).isEqualTo(1L);
    assertThat(candidate.getName()).isEqualTo("suzy");
  }

  @Test
  public void 후보자생성_이미지있음() throws Exception {
    //given
    Candidate candidate = createCandidate(1);
    candidate.addGallery(new CandidateGallery("image1"));
    candidate.addGallery(new CandidateGallery("image2"));
    candidate.addGallery(new CandidateGallery("image3"));

    //when
    Candidate savedCandidate = candidateRepository.save(candidate);
    Candidate findCandidate = candidateRepository.findById(savedCandidate.getId()).orElseThrow();

    //then
    assertThat(findCandidate.getGalleries().get(0).getImagePath()).isEqualTo("image1");
    assertThat(findCandidate.getGalleries().get(1).getImagePath()).isEqualTo("image2");
    assertThat(findCandidate.getGalleries().get(2).getImagePath()).isEqualTo("image3");
  }


  @Test
  public void 후보자삭제() throws Exception {
    //given
    Candidate candidate = createCandidate(1);
    candidateRepository.save(candidate);

    //when
    candidateRepository.delete(candidate);

    //then
    assertThat(candidateRepository.existsById(candidate.getId())).isFalse();

  }

  private Candidate createCandidate(Integer index) {

    return Candidate.builder()
        .contractIndex(index)
        .name("suzy")
        .profile("profile")
        .poll(null)
        .thumbnail("thumbnail")
        .build();
  }
}
