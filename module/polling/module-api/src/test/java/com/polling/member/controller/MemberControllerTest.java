package com.polling.member.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.exception.CustomExceptionHandler;
import com.polling.member.dto.request.SaveNativeMemberRequestDto;
import com.polling.member.service.MemberService;
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

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@EnableWebSecurity
public class MemberControllerTest {

  private final String email = "test@email.com";
  private final String nickname = "testNickname";
  @InjectMocks
  private MemberController target;
  @Mock
  private MemberService memberService;
  private Gson gson;
  private MockMvc mockMvc;

  @BeforeEach
  public void init() {
    gson = new Gson();
    mockMvc = MockMvcBuilders.standaloneSetup(target)
        .setControllerAdvice(new CustomExceptionHandler())
        .build();
  }

  @Test
  public void mockMvcIsNotNull() throws Exception {
    assertThat(target).isNotNull();
    assertThat(mockMvc).isNotNull();
  }

  @Test
  public void 회원가입실패_이메일중복() throws Exception {
    //given
    final String url = "/api/members";
    SaveNativeMemberRequestDto requestDto = SaveNativeMemberRequestDto.builder().build();
    doThrow(new CustomException(CustomErrorResult.DUPLICATE_EMAIL))
        .when(memberService)
        .join(any(SaveNativeMemberRequestDto.class));

    //when
    ResultActions resultActions = mockMvc.perform(post(url)
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().is4xxClientError());
  }

  @Test
  public void 회원가입성공() throws Exception {
    //given
    final String url = "/api/members";
    SaveNativeMemberRequestDto requestDto = SaveNativeMemberRequestDto.builder().build();

    //when
    ResultActions resultActions = mockMvc.perform(post(url)
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().isOk());
    verify(memberService, times(1)).join(any(SaveNativeMemberRequestDto.class));
  }

  @Test
  public void 닉네임체크실패_닉네임중복() throws Exception {
    //given
    final String url = "/api/members/nickname/{nickname}";
    final String nickname = "testNickname";
    doThrow(new CustomException(CustomErrorResult.DUPLICATE_NICKNAME))
        .when(memberService)
        .checkDuplicateMemberNickname(nickname);

    //when
    ResultActions resultActions = mockMvc.perform(get(url, nickname));

    //then
    resultActions.andExpect(status().is4xxClientError());
  }

  @Test
  public void 닉네임체크성공() throws Exception {
    //given
    final String url = "/api/members/nickname/{nickname}";
    final String nickname = "testNickname";

    //when
    ResultActions resultActions = mockMvc.perform(get(url, nickname));

    //then
    resultActions.andExpect(status().isOk());
    verify(memberService, times(1)).checkDuplicateMemberNickname(nickname);
  }
}
