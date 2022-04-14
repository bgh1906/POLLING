package com.polling.poll.candidate.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.dto.response.FindCandidateHistoryResponseDto;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import com.polling.poll.candidate.entity.CandidateHistory;
import com.polling.poll.poll.entity.Poll;
import com.polling.poll.poll.repository.PollRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class CandidateHistoryQueryRepositoryTest {

  @Autowired
  private CandidateRepository candidateRepository;
  @Autowired
  private CandidateQueryRepository candidateQueryRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private PollRepository pollRepository;

  @Test
  public void 후보자투표내역조회() throws Exception {
    //given
    Candidate savedCandidate = candidateRepository.save(createCandidate(1));
    Candidate anotherCandidate = candidateRepository.save(createCandidate(2));
    Member savedMember = memberRepository.save(Member.builder().build());
    vote(savedMember, savedCandidate, 1);
    vote(savedMember, savedCandidate, 1);
    vote(savedMember, savedCandidate, 1);
    vote(savedMember, savedCandidate, 1);
    vote(savedMember, savedCandidate, 1);
    vote(savedMember, anotherCandidate, 1);

    //when
    List<FindCandidateHistoryResponseDto> result = candidateQueryRepository.findHistoryById(
        savedCandidate.getId(), 0, 10);

    //then
    assertThat(result.size()).isEqualTo(5);
    assertThat(result.get(0).getVoteCount()).isEqualTo(1);
  }

  @Test
  public void 후보자투표내역조회_오늘투표했는지() throws Exception {
    //given
    Poll poll = Poll.builder().build();
    Candidate savedCandidate = candidateRepository.save(createCandidate(1));
    poll.addCandidate(savedCandidate);
    Long pollId = pollRepository.save(poll).getId();
    Member savedMember = memberRepository.save(Member.builder().build());
    vote(savedMember, savedCandidate, 1);

    //when
    boolean result = candidateQueryRepository.existsByMemberIdAndPollIdInToday(savedMember.getId(),
        pollId, LocalDate.now().atStartOfDay());

    //then
    assertThat(result).isTrue();
  }

  public void vote(Member member, Candidate candidate, int count) {
    candidate.addHistory(CandidateHistory
            .createCandidateHistory(member, candidate, count, null));
  }

  public Candidate createCandidate(Integer index) {
    Candidate candidate = Candidate.builder()
        .contractIndex(index)
        .thumbnail("thumbnail")
        .profile("profile")
        .name("name" + index)
        .build();
    candidate.addGallery(new CandidateGallery("image1"));
    candidate.addGallery(new CandidateGallery("image2"));
    candidate.addGallery(new CandidateGallery("image3"));

    return candidate;
  }
}
