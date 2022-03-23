package com.polling.poll.service;

import com.polling.candidate.dto.request.SaveCandidateRequestDto;
import com.polling.entity.member.Member;
import com.polling.entity.poll.Poll;
import com.polling.entity.poll.status.ShowStatus;
import com.polling.poll.dto.request.SavePollRequestDto;
import com.polling.repository.member.MemberRepository;
import com.polling.repository.poll.PollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
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
    public void 투표생성() throws Exception{
        //given
        List<SaveCandidateRequestDto> candidateRequestDtos = new ArrayList<>();
        candidateRequestDtos.add(new SaveCandidateRequestDto("candidateName1", "hello", new ArrayList<>()));
        candidateRequestDtos.add(new SaveCandidateRequestDto("candidateName2", "hello", new ArrayList<>()));
        String format = "2022-04-01";
        LocalDateTime current = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        SavePollRequestDto savePollRequestDto = SavePollRequestDto.builder()
                .title("testTitle")
                .content("testContent")
                .showStatus(ShowStatus.SHOW_ALL)
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
        doReturn(Optional.of(pollCreator)).when(memberRepository).findByEmail(pollCreator.getEmail());
        doReturn(Poll.builder().build()).when(pollRepository).save(any(Poll.class));
        target.savePoll(savePollRequestDto, pollCreator.getEmail());

        //then
        verify(memberRepository, times(1)).findByEmail(pollCreator.getEmail());
        verify(pollRepository, times(1)).save(any(Poll.class));
    }
}
