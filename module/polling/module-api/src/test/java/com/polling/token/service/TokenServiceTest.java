package com.polling.token.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import com.polling.poll.candidate.repository.CandidateGalleryRepository;
import com.polling.poll.candidate.repository.CandidateRepository;
import com.polling.token.dto.request.SaveTokenUsageHistoryRequestDto;
import com.polling.token.entity.TokenUsageHistory;
import com.polling.token.repository.TokenUsageHistoryRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

  @InjectMocks
  private TokenService target;
  @Mock
  private TokenUsageHistoryRepository tokenUsageHistoryRepository;
  @Mock
  private MemberRepository memberRepository;
  @Mock
  private CandidateGalleryRepository candidateGalleryRepository;
  @Mock
  private CandidateRepository candidateRepository;

  @Test
  public void TokenServiceIsNotNull() throws Exception {
    assertThat(target).isNotNull();
  }

  @Test
  public void 유저가시크릿이미지에토큰사용() throws Exception {
    //given
    SaveTokenUsageHistoryRequestDto requestDto =
        new SaveTokenUsageHistoryRequestDto(1L, 1L);
    doReturn(Optional.of(Member.builder().build())).when(memberRepository).findById(anyLong());
    doReturn(Optional.of(Candidate.builder().build())).when(candidateRepository)
        .findById(anyLong());
    doReturn(Optional.of(new CandidateGallery("image"))).when(candidateGalleryRepository)
        .findById(anyLong());

    //when
    target.saveMemberTokenUsageToCandidate(requestDto, 1L);

    //then
    verify(tokenUsageHistoryRepository, times(1)).save(any(TokenUsageHistory.class));
  }
}
