package com.polling.poll.queryrepository.candidate;

import com.polling.poll.dto.candidate.response.FindCandidateHistoryResponseDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.candidate.CandidateHistory;
import com.polling.entity.member.Member;
import com.polling.entity.poll.Poll;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import com.polling.repository.candidate.CandidateHistoryRepository;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.member.MemberRepository;
import com.polling.repository.poll.PollRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class CandidateHistoryQueryRepositoryTest {
    @Autowired
    private CandidateHistoryQueryRepository queryRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateHistoryRepository candidateHistoryRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PollRepository pollRepository;

    @Test
    public void 후보자투표내역조회() throws Exception{
        //given
        Candidate savedCandidate = candidateRepository.save(Candidate.builder().build());
        Candidate anotherCandidate = candidateRepository.save(Candidate.builder().build());
        Member savedMember = memberRepository.save(Member.builder().build());
        vote(savedMember, savedCandidate, 1);
        vote(savedMember, savedCandidate, 1);
        vote(savedMember, savedCandidate, 1);
        vote(savedMember, savedCandidate, 1);
        vote(savedMember, savedCandidate, 1);
        vote(savedMember, anotherCandidate, 1);

        //when
        List<FindCandidateHistoryResponseDto> result = queryRepository.findByCandidateId(savedCandidate.getId(), 0, 10);

        //then
        assertThat(result.size()).isEqualTo(5);
        assertThat(result.get(0).getVoteCount()).isEqualTo(1);
    }

    @Test
    public void 후보자투표내역조회_오늘투표했는지() throws Exception{
        //given
        Poll savedPoll = pollRepository.save(Poll.builder().build());
        Candidate savedCandidate = candidateRepository.save(Candidate.builder().poll(savedPoll).build());
        Member savedMember = memberRepository.save(Member.builder().build());
        vote(savedMember, savedCandidate, 1);
        Long pollId = savedCandidate.getPoll().getId();

        //when
        boolean result = queryRepository.existsByMemberIdAndPollIdInToday(savedMember.getId(), pollId, LocalDate.now().atStartOfDay());

        //then
        assertThat(result).isTrue();
    }

    public CandidateHistory vote(Member member, Candidate candidate, int count){
        return candidateHistoryRepository.save(CandidateHistory.builder()
                .candidate(candidate)
                .member(member)
                .voteCount(1)
                .build());
    }
}
