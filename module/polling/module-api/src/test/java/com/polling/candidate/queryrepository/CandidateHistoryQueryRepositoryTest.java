package com.polling.candidate.queryrepository;

import com.polling.candidate.dto.response.FindPollHistoryResponseDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.candidate.CandidateHistory;
import com.polling.entity.member.Member;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import com.polling.repository.candidate.CandidateHistoryRepository;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
        List<FindPollHistoryResponseDto> result = queryRepository.findByCandidateId(savedCandidate.getId(), 0, 10);

        //then
        assertThat(result.size()).isEqualTo(5);
        assertThat(result.get(0).getVoteCount()).isEqualTo(1);
    }

    public CandidateHistory vote(Member member, Candidate candidate, int count){
        return candidateHistoryRepository.save(CandidateHistory.builder()
                .candidate(candidate)
                .member(member)
                .voteCount(1)
                .build());
    }
}
