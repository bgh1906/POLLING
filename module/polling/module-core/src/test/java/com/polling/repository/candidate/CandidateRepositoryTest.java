package com.polling.repository.candidate;


import static org.assertj.core.api.Assertions.assertThat;

import com.polling.config.JpaConfig;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.candidate.CandidateGallery;
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
    Long saveCandidateId = candidateRepository.save(createCandidate("suzy")).getId();

    //then
    Candidate candidate = candidateRepository.findById(saveCandidateId).orElseThrow();
    assertThat(candidate.getName()).isEqualTo("suzy");
  }

  @Test
  public void 후보자생성_이미지있음() throws Exception {
    //given
    Candidate candidate = createCandidate("suzy");
    candidate.addGallery(new CandidateGallery("image1"));
    candidate.addGallery(new CandidateGallery("image2"));
    candidate.addGallery(new CandidateGallery("image3"));

    //when
    Long saveCandidateId = candidateRepository.save(candidate).getId();

    //then
    Candidate findCandidate = candidateRepository.findById(saveCandidateId).orElseThrow();
    assertThat(findCandidate.getGalleries().get(0).getImagePath()).isEqualTo("image1");
    assertThat(findCandidate.getGalleries().get(1).getImagePath()).isEqualTo("image2");
    assertThat(findCandidate.getGalleries().get(2).getImagePath()).isEqualTo("image3");
  }

  @Test
  public void 후보자투표수증가() throws Exception {
    //given
    Candidate candidate = createCandidate("suzy");
    candidateRepository.save(candidate);

    //when
    candidate.addVoteTotal(1);
    Candidate findCandidate = candidateRepository.findById(candidate.getId()).orElseThrow();

    //then
    assertThat(findCandidate.getVoteTotalCount()).isEqualTo(1);
  }

  @Test
  public void 후보자삭제() throws Exception {
    //given
    Candidate candidate = createCandidate("suzy");
    candidateRepository.save(candidate);

    //when
    candidateRepository.delete(candidate);

    //then
    assertThat(candidateRepository.existsById(candidate.getId())).isFalse();

  }

  private Candidate createCandidate(String name) {
    Candidate candidate = Candidate.builder()
        .name(name)
        .profile("profile")
        .poll(null)
        .thumbnail("thumbnail")
        .build();

    return candidate;
  }
}
