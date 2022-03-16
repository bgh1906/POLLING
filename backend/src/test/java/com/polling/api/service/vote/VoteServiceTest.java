package com.polling.api.service.vote;

import com.polling.api.controller.candidate.dto.request.SaveCandidateRequestDto;
import com.polling.api.controller.vote.dto.request.SaveVoteRequestDto;
import com.polling.api.controller.vote.dto.response.FindVoteResponseDto;
import com.polling.api.queryrepository.VoteQueryRepository;
import com.polling.core.entity.candidate.Candidate;
import com.polling.core.entity.member.Member;
import com.polling.core.entity.vote.Vote;
import com.polling.core.entity.vote.status.VoteStatus;
import com.polling.core.repository.candidate.CandidateRepository;
import com.polling.core.repository.member.MemberRepository;
import com.polling.core.repository.vote.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Transactional
@SpringBootTest
class VoteServiceTest {
    @Autowired
    VoteService voteService;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    VoteQueryRepository voteQueryRepository;
    @Autowired
    CandidateRepository candidateRepository;

    private String hostEmail = "polling@test.com";

    @BeforeEach
    void init(){
        Member admin = Member.builder()
                .name("관리자")
                .email(hostEmail)
                .password("sasds")
                .phoneNumber("01099123127")
                .build();
        memberRepository.save(admin);
    }

    @DisplayName("투표와 후보자들을 저장")
    @Test
    void saveVoteTest(){
        //given
        SaveVoteRequestDto requestDto = getSaveVoteRequest();

        //when
        Long id = voteService.saveVote(requestDto, hostEmail);
        Vote vote = voteRepository.findById(id).get();

        //then
        assertEquals(vote.getName(), "못말리는신짱");
    }

    @DisplayName("투표 랭킹 리스트")
    @Test
    void getRankingTest(){
        //given
        SaveVoteRequestDto requestDto = getSaveVoteRequest();
        Long id = voteService.saveVote(requestDto, hostEmail);
        Vote vote = voteRepository.findById(id).orElseThrow(RuntimeException::new);
        Candidate candidate = candidateRepository.findByName("짱구").orElseThrow(RuntimeException::new);
        voteService.voteTo(candidate.getId(), 100);

        //when
        FindVoteResponseDto responseDto = voteService.getRanking(vote.getId());

        //then
        assertEquals(responseDto.getCandidates().get(0).getName(), "짱구");
    }

    @DisplayName("투표 삭제")
    @Test
    void deleteTest(){
        //given
        SaveVoteRequestDto requestDto = getSaveVoteRequest();
        Long id = voteService.saveVote(requestDto, hostEmail);

        //when
        voteService.delete(id);

        //then
        assertThatThrownBy(() -> {
            voteRepository.findById(id).orElseThrow(RuntimeException::new);
        }).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("투표 종료")
    @Test
    void endVoteTest(){
        //given
        SaveVoteRequestDto requestDto = getSaveVoteRequest();
        Long id = voteService.saveVote(requestDto, hostEmail);

        //when
        voteService.endVote(id);

        //then
        Vote vote = voteRepository.findById(id).orElseThrow(RuntimeException::new);
        assertEquals(vote.getVoteStatus(), VoteStatus.COMPLETE);
    }


    SaveVoteRequestDto getSaveVoteRequest(){
        Member admin = memberRepository.findAll().get(0);

        List<SaveCandidateRequestDto> candidates = new ArrayList<SaveCandidateRequestDto>();
        SaveCandidateRequestDto requestDto1 = SaveCandidateRequestDto.builder()
                .name("짱구")
                .profilePath("somewhere")
                .build();
        SaveCandidateRequestDto requestDto2 = SaveCandidateRequestDto.builder()
                .name("짱아")
                .profilePath("somewhere")
                .build();
        candidates.add(requestDto1);
        candidates.add(requestDto2);

        return SaveVoteRequestDto.builder()
                .candidates(candidates)
                .name("못말리는신짱")
                .content("짱구네")
                .startDate(LocalDateTime.MIN)
                .endDate(LocalDateTime.MAX)
                .build();
    }
}