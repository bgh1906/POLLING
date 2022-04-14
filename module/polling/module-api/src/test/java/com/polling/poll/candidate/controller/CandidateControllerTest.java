package com.polling.poll.candidate.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.exception.CustomExceptionHandler;
import com.polling.poll.candidate.repository.CandidateQueryRepository;
import com.polling.poll.candidate.service.CandidateService;
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

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@EnableWebSecurity
public class CandidateControllerTest {

  @InjectMocks
  private CandidateController target;

  @Mock
  private CandidateService candidateService;

  @Mock
  private CandidateQueryRepository queryRepository;

  private MockMvc mockMvc;

  @BeforeEach
  public void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(target)
        .setControllerAdvice(new CustomExceptionHandler())
        .build();
  }

  @Test
  public void candidateControllerIsNotNull() throws Exception {
    assertThat(target).isNotNull();
  }

  @Test
  public void 후보자단일조회_후보자찾기실패() throws Exception {
    //given
    final String url = "/api/polls/candidates/{candidatesId}";
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
  public void 후보자단일조회_성공() throws Exception {
    //given
    final String url = "/api/polls/candidates/{candidatesId}";
    Long candidatesId = 1L;

    //when
    ResultActions resultActions = mockMvc.perform(get(url, candidatesId));

    //then
    resultActions.andExpect(status().isOk());
    verify(candidateService, times(1)).getProfile(candidatesId);
  }

  @Test
  public void 후보자페이징조회_득표내역기준() throws Exception {
    //given
    final String url = "/api/polls/candidates/{candidatesId}/{page}/{limit}";
    Long candidatesId = 1L;
    int page = 0;
    int limit = 50;

    //when
    ResultActions resultActions = mockMvc.perform(get(url, candidatesId, page, limit));

    //then
    resultActions.andExpect(status().isOk());
    verify(queryRepository, times(1)).findHistoryById(candidatesId, page, limit);
  }

}
