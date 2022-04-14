package com.polling.poll.poll.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.entity.status.MemberRole;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.dto.request.SaveCandidateRequestDto;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import com.polling.poll.candidate.repository.CandidateQueryRepository;
import com.polling.poll.poll.dto.request.SavePollRequestDto;
import com.polling.poll.poll.entity.Poll;
import com.polling.poll.poll.repository.PollRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PollServiceTest {

  @InjectMocks
  private PollService target;

  @Mock
  private MemberRepository memberRepository;
  @Mock
  private PollRepository pollRepository;
  @Mock
  private CandidateQueryRepository candidateQueryRepository;

  @Test
  public void pollServiceIsNotNull() throws Exception {
    assertThat(target).isNotNull();
  }

  @Test
  public void 투표생성실패_허용되지않은권한() throws Exception {
    //given
    List<SaveCandidateRequestDto> candidateRequestDtos = new ArrayList<>();
    candidateRequestDtos.add(SaveCandidateRequestDto.builder().build());
    candidateRequestDtos.add(SaveCandidateRequestDto.builder().build());

    String format = "2022-04-01";
    LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        .atStartOfDay();
    SavePollRequestDto requestDto = new SavePollRequestDto(
        null,
        "title",
        "content",
        "thumbnail",
        true,
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(10));
    Member pollCreator = Member.builder()
        .email("testEmail")
        .nickname("hostNickname")
        .password("testPassword")
        .build();

    //when
    doReturn(Optional.of(pollCreator)).when(memberRepository).findById(anyLong());
    Throwable exception = Assertions.assertThrows(CustomException.class, () -> {
      target.savePoll(requestDto, 1L);
    });

    //then
    assertThat(exception.getMessage()).isEqualTo(
        new CustomException(CustomErrorResult.UNAUTHORIZED_MEMBER_ROLE).getMessage());
  }

  @Test
  public void 투표생성성공() throws Exception {
    //given
    List<SaveCandidateRequestDto> candidateRequestDtos = new ArrayList<>();
    candidateRequestDtos.add(SaveCandidateRequestDto.builder().build());
    candidateRequestDtos.add(SaveCandidateRequestDto.builder().build());

    String format = "2022-04-01";
    LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        .atStartOfDay();
    SavePollRequestDto requestDto = new SavePollRequestDto(
        candidateRequestDtos,
        "title",
        "content",
        "thumbnail",
        true,
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(10));
    Member pollCreator = Member.builder()
        .email("testEmail")
        .nickname("hostNickname")
        .password("testPassword")
        .build();
    pollCreator.addRole(MemberRole.ROLE_ADMIN);

    //when
    doReturn(Optional.of(pollCreator)).when(memberRepository).findById(anyLong());
    doReturn(Poll.builder().build()).when(pollRepository).save(any(Poll.class));
    target.savePoll(requestDto, 1L);

    //then
    verify(memberRepository, times(1)).findById(anyLong());
    verify(pollRepository, times(1)).save(any(Poll.class));
  }

  @Test
  public void 투표조회_모든정보() throws Exception {
    //given
    Poll poll = Poll.builder().build();
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(createCandidate("test1"));
    candidates.add(createCandidate("test2"));
    candidates.add(createCandidate("test3"));

    //when
    doReturn(Optional.of(poll)).when(pollRepository).findById(anyLong());
    target.findPollAllInfo(1L);

    //then
    verify(pollRepository, times(1)).findById(anyLong());
  }

  @Test
  public void 투표조회_간략정보() throws Exception {
    //given
    Poll poll = Poll.builder().build();

    //when
    doReturn(Optional.of(poll)).when(pollRepository).findById(anyLong());
    target.findPollThumbnail(1L);

    //then
    verify(pollRepository, times(1)).findById(anyLong());
  }

  private Candidate createCandidate(String name) {
    Candidate candidate = Candidate.builder()
        .name(name)
        .profile("profile")
        .poll(null)
        .thumbnail("thumbnail")
        .build();
    candidate.addGallery(new CandidateGallery("123"));
    candidate.addGallery(new CandidateGallery("456"));
    candidate.addGallery(new CandidateGallery("789"));

    return candidate;
  }
}
