package com.polling.poll.candidate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.nullable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.dto.request.ModifyCandidateRequestDto;
import com.polling.poll.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.repository.CandidateQueryRepository;
import com.polling.poll.candidate.repository.CandidateRepository;
import com.polling.poll.poll.entity.Poll;
import com.polling.poll.poll.entity.status.PollStatus;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

  @InjectMocks
  private CandidateService target;

  @Mock
  private CandidateRepository candidateRepository;
  @Mock
  private CandidateQueryRepository candidateQueryRepository;
  @Mock
  private MemberRepository memberRepository;


  @Test
  public void candidateServiceIsNotNull() throws Exception {
    assertThat(target).isNotNull();
  }

  @Test
  public void 후보자에투표실패_0이하의투표() throws Exception {
    //given
    final SaveCandidateHistoryRequestDto requestDto = new SaveCandidateHistoryRequestDto(
        1L,
        "transaction_id",
        0);

    //when
    final CustomException result = assertThrows(CustomException.class,
        () -> target.saveVoteHistory(requestDto, 1L));

    //then
    assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.INVALID_VOTES);
  }

  @Test
  public void 후보자에투표실패_없는유저() throws Exception {
    //given
    final SaveCandidateHistoryRequestDto requestDto = new SaveCandidateHistoryRequestDto(
        1L,
        "transaction_id",
        1);

    doThrow(new CustomException(CustomErrorResult.USER_NOT_FOUND)).when(memberRepository)
        .findById(1L);

    //when
    final CustomException result = assertThrows(CustomException.class,
        () -> target.saveVoteHistory(requestDto, 1L));

    //then
    assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.USER_NOT_FOUND);
  }

  @Test
  public void 후보자에투표실패_없는후보자() throws Exception {
    //given
    final SaveCandidateHistoryRequestDto requestDto = new SaveCandidateHistoryRequestDto(
        1L,
        "transaction_id",
        1);
    doReturn(Optional.of(Member.builder().build())).when(memberRepository).findById(1L);
    doThrow(new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND)).when(
        candidateRepository).findById(1L);

    //when
    final CustomException result = assertThrows(CustomException.class,
        () -> target.saveVoteHistory(requestDto, 1L));

    //then
    assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.CANDIDATE_NOT_FOUND);
  }

  @Test
  public void 후보자에투표실패_중복투표() throws Exception {
    //given
    final SaveCandidateHistoryRequestDto requestDto = new SaveCandidateHistoryRequestDto(
        1L,
        "transaction_id",
        1);

    doReturn(Optional.of(Member.builder().build())).when(memberRepository).findById(1L);
    doReturn(Optional.of(Candidate.builder()
        .poll(Poll.builder().build())
        .build())).when(candidateRepository).findById(1L);
    doReturn(true).when(candidateQueryRepository)
        .existsByMemberIdAndPollIdInToday(nullable(Long.class), nullable(Long.class),
            any(LocalDateTime.class));

    //when
    final CustomException result = assertThrows(CustomException.class,
        () -> target.saveVoteHistory(requestDto, 1L));

    //then
    assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.ALREADY_VOTES);
  }

  @Test
  public void 후보자에투표성공() throws Exception {
    //given
    Long memberId = 1L;
    long candidateId = 1L;
    final SaveCandidateHistoryRequestDto requestDto = new SaveCandidateHistoryRequestDto(
        1L,
        "transaction_id",
        1);

    doReturn(Optional.of(Member.builder().build())).when(memberRepository).findById(memberId);
    doReturn(Optional.of(Candidate.builder()
        .poll(Poll.builder().build())
        .build())).when(candidateRepository).findById(candidateId);
    doReturn(false).when(candidateQueryRepository)
        .existsByMemberIdAndPollIdInToday(nullable(Long.class), nullable(Long.class),
            any(LocalDateTime.class));

    //when
    target.saveVoteHistory(requestDto, memberId);

    //then
    verify(memberRepository, times(1)).findById(memberId);
    verify(candidateRepository, times(1)).findById(candidateId);
    verify(candidateQueryRepository, times(1))
        .existsByMemberIdAndPollIdInToday(nullable(Long.class), nullable(Long.class),
            any(LocalDateTime.class));
  }

  @Test
  public void 후보자삭제실패_없는후보자() throws Exception {
    //given
    doReturn(false).when(candidateRepository).existsById(anyLong());

    //when
    final CustomException result = assertThrows(CustomException.class,
        () -> target.deleteCandidate(1L));

    //then
    assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.CANDIDATE_NOT_FOUND);
  }

  @Test
  public void 후보자삭제성공() throws Exception {
    //given
    doReturn(true).when(candidateRepository).existsById(anyLong());

    //when
    target.deleteCandidate(1L);

    //then
    verify(candidateRepository, times(1)).existsById(1L);
    verify(candidateRepository, times(1)).deleteById(1L);
    verify(candidateQueryRepository, times(1)).deleteGalleryById(1L);
  }

  @Test
  public void 후보자수정실패_없는후보자() throws Exception {
    //given
    final ModifyCandidateRequestDto requestDto = new ModifyCandidateRequestDto(
        "name",
        "profile",
        "image1",
        "image2",
        "image3",
        "thumbnail"
    );

    //when
    final CustomException result = assertThrows(CustomException.class,
        () -> target.modifyCandidate(1L, requestDto));

    //then
    assertThat(result.getCustomErrorResult()).isEqualTo(CustomErrorResult.CANDIDATE_NOT_FOUND);
  }

  @Test
  public void 후보자수정실패_허용되지않은상태() throws Exception {
    //given
    final ModifyCandidateRequestDto requestDto = new ModifyCandidateRequestDto(
        "name",
        "profile",
        "image1",
        "image2",
        "image3",
        "thumbnail"
    );
    Candidate candidate = Candidate.builder().build();
    Poll poll = Poll.builder().build();
    poll.changePollStatus(PollStatus.IN_PROGRESS);
    poll.addCandidate(candidate);

    doReturn(Optional.of(candidate)).when(candidateRepository).findById(anyLong());

    //when
    final CustomException result = assertThrows(CustomException.class,
        () -> target.modifyCandidate(1L, requestDto));

    //then
    assertThat(result.getCustomErrorResult()).isEqualTo(
        CustomErrorResult.IMPOSSIBLE_STATUS_TO_MODIFY);
  }

  @Test
  public void 후보자수정성공() throws Exception {
    //given
    final ModifyCandidateRequestDto requestDto = new ModifyCandidateRequestDto(
        "name",
        "profile",
        "image1",
        "image2",
        "image3",
        "thumbnail"
    );
    Candidate candidate = Candidate.builder().build();
    Poll poll = Poll.builder().build();
    poll.changePollStatus(PollStatus.WAIT);
    poll.addCandidate(candidate);

    doReturn(Optional.of(candidate)).when(candidateRepository).findById(anyLong());

    //when
    target.modifyCandidate(1L, requestDto);

    //then
    verify(candidateRepository, times(1)).findById(1L);
    verify(candidateQueryRepository, times(1)).deleteGalleryById(1L);
  }


}
