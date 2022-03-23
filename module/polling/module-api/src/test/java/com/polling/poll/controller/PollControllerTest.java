package com.polling.poll.controller;

import com.google.gson.Gson;
import com.polling.entity.member.status.MemberRole;
import com.polling.entity.poll.Poll;
import com.polling.exception.CustomExceptionHandler;
import com.polling.member.controller.MemberController;
import com.polling.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.member.service.MemberService;
import com.polling.poll.dto.response.FindPollPageResponseDto;
import com.polling.poll.dto.response.FindPollResponseDto;
import com.polling.poll.service.PollService;
import com.polling.queryrepository.PollQueryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@EnableWebSecurity
public class PollControllerTest {
    @InjectMocks
    private PollController target;

    @Mock
    private PollService pollService;

    @Mock
    private PollQueryRepository pollQueryRepository;

    private Gson gson;
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }
    
    @Test
    public void pollControllerIsNotNull() throws Exception{
        Assertions.assertThat(target).isNotNull();
    }

    @Test
    public void 득표현황랭킹조회() throws Exception{
        //given
        final String url = "/api/votes/{id}";
        doReturn(FindPollResponseDto.builder().build()).when(pollService).getRanking(1L);

        //when
        ResultActions resultActions = mockMvc.perform(get(url, 1L));

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void 투표조회_진행중() throws Exception{
        //given
        final String url = "/api/votes/{page}/{limit}";
        doReturn(new ArrayList<FindPollPageResponseDto>()).when(pollQueryRepository).findProgressPollPage(0, 10);

        //when
        ResultActions resultActions = mockMvc.perform(get(url, 0, 10));

        //then
        resultActions.andExpect(status().isOk());
        verify(pollQueryRepository, times(1)).findProgressPollPage(0,10);
    }

    @Test
    public void 투표조회_종료된() throws Exception{
        //given
        final String url = "/api/votes/end/{page}/{limit}";
        doReturn(new ArrayList<FindPollPageResponseDto>()).when(pollQueryRepository).findDonePollPage(0, 10);

        //when
        ResultActions resultActions = mockMvc.perform(get(url, 0, 10));

        //then
        resultActions.andExpect(status().isOk());
        verify(pollQueryRepository, times(1)).findDonePollPage(0,10);
    }


}
