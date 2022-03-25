package com.polling.candidate.service;

import com.polling.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.member.Member;
import com.polling.entity.poll.Poll;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {
    @InjectMocks
    private CandidateService target;

    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private CandidateHistoryQueryRepository queryRepository;


    @Test
    public void candidateServiceIsNotNull() throws Exception{
        assertThat(target).isNotNull();
    }

    @Test
    public void 후보자에투표실패_0이하의투표() throws Exception{
        //given
        final SaveCandidateHistoryRequestDto requestDto = SaveCandidateHistoryRequestDto.builder()
                .candidateId(1L)
                .voteCount(0)
                .build();

        //when
        final CustomException result = assertThrows(CustomException.class, () -> target.voteToCandidate(requestDto, 1L));

        //then
        assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.INVALID_VOTES);
    }

    @Test
    public void 후보자에투표실패_없는유저() throws Exception{
        //given
        final SaveCandidateHistoryRequestDto requestDto = SaveCandidateHistoryRequestDto.builder()
                .candidateId(1L)
                .voteCount(1)
                .build();
        doThrow(new CustomException(CustomErrorResult.USER_NOT_FOUND)).when(memberRepository).findById(1L);

        //when
        final CustomException result = assertThrows(CustomException.class, () -> target.voteToCandidate(requestDto, 1L));

        //then
        assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.USER_NOT_FOUND);
    }

    @Test
    public void 후보자에투표실패_없는후보자() throws Exception{
        //given
        final SaveCandidateHistoryRequestDto requestDto = SaveCandidateHistoryRequestDto.builder()
                .candidateId(1L)
                .voteCount(1)
                .build();
        doReturn(Optional.of(Member.builder().build())).when(memberRepository).findById(1L);
        doThrow(new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND)).when(candidateRepository).findById(1L);

        //when
        final CustomException result = assertThrows(CustomException.class, () -> target.voteToCandidate(requestDto, 1L));

        //then
        assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.CANDIDATE_NOT_FOUND);
    }

    @Test
    public void 후보자에투표실패_중복투표() throws Exception{
        //given
        final SaveCandidateHistoryRequestDto requestDto = SaveCandidateHistoryRequestDto.builder()
                .candidateId(1L)
                .voteCount(1)
                .build();

        doReturn(Optional.of(Member.builder().build())).when(memberRepository).findById(1L);
        doReturn(Optional.of(Candidate.builder()
                .poll(Poll.builder().build())
                .build())).when(candidateRepository).findById(1L);
        doReturn(true).when(queryRepository).existsByMemberIdAndPollIdInToday(nullable(Long.class), nullable(Long.class), any(LocalDateTime.class));

        //when
        final CustomException result = assertThrows(CustomException.class, () -> target.voteToCandidate(requestDto, 1L));

        //then
        assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.ALREADY_VOTES);
    }

    @Test
    public void 후보자에투표성공() throws Exception{
        //given
        Long memberId = 1L;
        long candidateId = 1L;
        final SaveCandidateHistoryRequestDto requestDto = SaveCandidateHistoryRequestDto.builder()
                .candidateId(candidateId)
                .voteCount(1)
                .build();

        doReturn(Optional.of(Member.builder().build())).when(memberRepository).findById(memberId);
        doReturn(Optional.of(Candidate.builder()
                .poll(Poll.builder().build())
                .build())).when(candidateRepository).findById(candidateId);
        doReturn(false).when(queryRepository).existsByMemberIdAndPollIdInToday(nullable(Long.class), nullable(Long.class), any(LocalDateTime.class));

        //when
        target.voteToCandidate(requestDto, memberId);

        //then
        verify(memberRepository, times(1)).findById(memberId);
        verify(candidateRepository, times(1)).findById(candidateId);
        verify(queryRepository, times(1))
                .existsByMemberIdAndPollIdInToday(nullable(Long.class), nullable(Long.class), any(LocalDateTime.class));
    }

}
