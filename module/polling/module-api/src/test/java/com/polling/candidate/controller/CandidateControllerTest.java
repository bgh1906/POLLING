package com.polling.candidate.controller;

import com.google.gson.Gson;
import com.polling.candidate.service.CandidateService;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.exception.CustomExceptionHandler;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@EnableWebSecurity
public class CandidateControllerTest {

    @InjectMocks
    private CandidateController target;

    @Mock
    private CandidateService candidateService;

    @Mock
    private CandidateHistoryQueryRepository queryRepository;

    private Gson gson;
    private MockMvc mockMvc;

    private final String email = "test@email.com";
    private final String nickname = "testNickname";

    @BeforeEach
    public void init(){
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    public void candidateControllerIsNotNull() throws Exception{
        assertThat(target).isNotNull();
    }

    @Test
    public void 후보자단일조회_후보자찾기실패() throws Exception{
        //given
        final String url = "/api/candidates/{candidatesId}";
        Long candidatesId = 1L;
        doThrow(new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND))
                .when(candidateService)
                .getProfile(candidatesId);

        //when
        ResultActions resultActions = mockMvc.perform(get(url, candidatesId));

        //then
        resultActions.andExpect(status().is4xxClientError());
        verify(candidateService, times(1)).getProfile(candidatesId);
    }

    @Test
    public void 후보자단일조회_성공() throws Exception{
        //given
        final String url = "/api/candidates/{candidatesId}";
        Long candidatesId = 1L;

        //when
        ResultActions resultActions = mockMvc.perform(get(url, candidatesId));

        //then
        resultActions.andExpect(status().isOk());
        verify(candidateService, times(1)).getProfile(candidatesId);
    }

    @Test
    public void 후보자페이징조회_득표내역기준() throws Exception{
        //given
        final String url = "/api/candidates/history/{candidatesId}/{page}/{limit}";
        Long candidatesId = 1L;
        int page = 0;
        int limit = 50;

        //when
        ResultActions resultActions = mockMvc.perform(get(url, candidatesId, page, limit));

        //then
        resultActions.andExpect(status().isOk());
        verify(queryRepository, times(1)).findByCandidateId(candidatesId, page, limit);
    }

}
