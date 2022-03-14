package com.ssafy.api.service.vote;

import com.ssafy.api.controller.candidate.dto.request.SaveCandidateRequestDto;
import com.ssafy.api.controller.vote.dto.request.SaveVoteRequestDto;
import com.ssafy.api.controller.vote.dto.response.FindVoteResponseDto;
import com.ssafy.api.queryrepository.VoteQueryRepository;
import com.ssafy.core.entity.candidate.Candidate;
import com.ssafy.core.entity.user.User;
import com.ssafy.core.entity.user.status.UserRole;
import com.ssafy.core.entity.vote.Vote;
import com.ssafy.core.entity.vote.status.VoteStatus;
import com.ssafy.core.repository.candidate.CandidateRepository;
import com.ssafy.core.repository.user.UserRepository;
import com.ssafy.core.repository.vote.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class VoteServiceTest {
    @Autowired
    VoteService voteService;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VoteQueryRepository voteQueryRepository;
    @Autowired
    CandidateRepository candidateRepository;

    @BeforeEach
    void init(){
        User admin = User.builder()
                .name("관리자")
                .email("ssafy@test.com")
                .password("sasds")
                .userRole(UserRole.ROLE_ADMIN)
                .phoneNumber("01099123127")
                .build();
        userRepository.save(admin);
    }

    @DisplayName("투표와 후보자들을 저장")
    @Test
    void saveVoteTest(){
        SaveVoteRequestDto requestDto = getSaveVoteRequest();

        //when
        Long id = voteService.saveVote(requestDto);
        Vote vote = voteRepository.findById(id).get();

        //then
        assertEquals(vote.getName(), "못말리는신짱");
    }

    @DisplayName("투표 랭킹 리스트")
    @Test
    void getRankingTest(){
        SaveVoteRequestDto requestDto = getSaveVoteRequest();
        Long id = voteService.saveVote(requestDto);
        Vote vote = voteRepository.findById(id).get();
        Candidate candidate = candidateRepository.findByName("짱구").get();
        voteService.voteTo(candidate.getId(), 100);
        //when
        FindVoteResponseDto responseDto = voteService.getRanking(vote.getId());

        //then
        assertEquals(responseDto.getCandidates().get(0).getName(), "짱구");
    }

    @DisplayName("투표 삭제")
    @Test
    void deleteTest(){
        SaveVoteRequestDto requestDto = getSaveVoteRequest();
        Long id = voteService.saveVote(requestDto);

        voteService.delete(id);

        assertThatThrownBy(() -> {
            voteRepository.findById(id).orElseThrow(RuntimeException::new);
        }).isInstanceOf(RuntimeException.class);
    }

    @DisplayName("투표 종료")
    @Test
    void endVoteTest(){
        SaveVoteRequestDto requestDto = getSaveVoteRequest();
        Long id = voteService.saveVote(requestDto);

        voteService.endVote(id);

        Vote vote = voteRepository.findById(id).get();
        assertEquals(vote.getVoteStatus(), VoteStatus.COMPLETE);
    }


    public SaveVoteRequestDto getSaveVoteRequest(){
        User admin = userRepository.findAll().get(0);

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

        SaveVoteRequestDto requestDto = SaveVoteRequestDto.builder()
                .candidates(candidates)
                .name("못말리는신짱")
                .content("짱구네")
                .startDate(LocalDateTime.MIN)
                .endDate(LocalDateTime.MAX)
                .hostId(admin.getId())
                .build();
        return requestDto;
    }



}