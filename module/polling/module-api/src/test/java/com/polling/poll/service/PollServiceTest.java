package com.polling.poll.service;

import com.polling.entity.member.status.MemberRole;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.poll.dto.candidate.request.SaveCandidateRequestDto;
import com.polling.entity.member.Member;
import com.polling.entity.poll.Poll;
import com.polling.poll.dto.request.SavePollRequestDto;
import com.polling.repository.member.MemberRepository;
import com.polling.repository.poll.PollRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PollServiceTest {
    @InjectMocks
    private PollService target;

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PollRepository pollRepository;

    @Test
    public void pollServiceIsNotNull() throws Exception{
        assertThat(target).isNotNull();
    }

    @Test
    public void 투표생성실패_허용되지않은권한() throws Exception{
        //given
        List<SaveCandidateRequestDto> candidateRequestDtos = new ArrayList<>();
        candidateRequestDtos.add(SaveCandidateRequestDto.builder().build());
        candidateRequestDtos.add(SaveCandidateRequestDto.builder().build());

        String format = "2022-04-01";
        LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        SavePollRequestDto savePollRequestDto = SavePollRequestDto.builder()
                .title("testTitle")
                .content("testContent")
                .startDate(current)
                .endDate(current.plusDays(10))
                .candidateDtos(candidateRequestDtos)
                .build();
        Member pollCreator = Member.builder()
                .email("testEmail")
                .nickname("hostNickname")
                .password("testPassword")
                .build();

        //when
        doReturn(Optional.of(pollCreator)).when(memberRepository).findById(anyLong());
        Throwable exception = Assertions.assertThrows(CustomException.class, () -> {
            target.savePoll(savePollRequestDto, 1L);
        });

        //then
        assertThat(exception.getMessage()).isEqualTo(new CustomException(CustomErrorResult.UNAUTHORIZED_MEMBER_ROLE).getMessage());
    }
    
    @Test
    public void 투표생성성공() throws Exception{
        //given
        List<SaveCandidateRequestDto> candidateRequestDtos = new ArrayList<>();
        candidateRequestDtos.add(SaveCandidateRequestDto.builder().build());
        candidateRequestDtos.add(SaveCandidateRequestDto.builder().build());

        String format = "2022-04-01";
        LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        SavePollRequestDto savePollRequestDto = SavePollRequestDto.builder()
                .title("testTitle")
                .content("testContent")
                .startDate(current)
                .endDate(current.plusDays(10))
                .candidateDtos(candidateRequestDtos)
                .build();
        Member pollCreator = Member.builder()
                .email("testEmail")
                .nickname("hostNickname")
                .password("testPassword")
                .build();
        pollCreator.addRole(MemberRole.ROLE_ADMIN);

        //when
        doReturn(Optional.of(pollCreator)).when(memberRepository).findById(anyLong());
        doReturn(Poll.builder().build()).when(pollRepository).save(any(Poll.class));
        target.savePoll(savePollRequestDto, 1L);

        //then
        verify(memberRepository, times(1)).findById(anyLong());
        verify(pollRepository, times(1)).save(any(Poll.class));
    }
}
