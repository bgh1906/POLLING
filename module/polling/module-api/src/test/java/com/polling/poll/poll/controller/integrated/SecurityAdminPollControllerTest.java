package com.polling.poll.poll.controller.integrated;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.polling.auth.dto.request.LoginRequestDto;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.poll.dto.request.SavePollRequestDto;
import com.polling.poll.poll.service.PollService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityAdminPollControllerTest {

  @MockBean
  private PollService pollService;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Gson gson;

  @BeforeEach
  public void setUp() {
    this.memberRepository.deleteAll();
  }

  @Test
  public void 투표생성_허용되지않은권한() throws Exception {
    //given
    final String url = "/api/polls/admin";
    SavePollRequestDto requestDto = new SavePollRequestDto(
        null,
        "title",
        "content",
        "thumbnail",
        true,
        null,
        null);
    doThrow(new CustomException(CustomErrorResult.UNAUTHORIZED_MEMBER))
        .when(pollService).savePoll(any(SavePollRequestDto.class), anyLong());

    //when
    ResultActions resultActions = mockMvc.perform(post(url)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().is4xxClientError());
  }


  @Test
  public void 투표생성_성공() throws Exception {
    //given
    final String url = "/api/polls/admin";
    SavePollRequestDto requestDto = new SavePollRequestDto(
        null,
        "title",
        "content",
        "thumbnail",
        true,
        null,
        null);

    //when
    ResultActions resultActions = mockMvc.perform(post(url)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().isOk());
    verify(pollService, times(1)).savePoll(any(SavePollRequestDto.class), anyLong());
  }

  @Test
  public void 투표삭제실패_투표가존재하지않음() throws Exception {
    //given
    final String url = "/api/polls/admin/{id}";
    Long id = 1L;
    doThrow(new CustomException(CustomErrorResult.VOTE_NOT_FOUND))
        .when(pollService).deletePoll(anyLong());

    //when
    ResultActions resultActions = mockMvc.perform(delete(url, id)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().is4xxClientError());
  }

  @Test
  public void 투표삭제_성공() throws Exception {
    //given
    final String url = "/api/polls/admin/{id}";
    Long id = 1L;

    //when
    ResultActions resultActions = mockMvc.perform(delete(url, id)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().isOk());
    verify(pollService, times(1)).deletePoll(anyLong());
  }

  public Member joinMember(int index) {
    return memberRepository.save(Member
        .builder()
        .email("test" + index + "@email.com")
        .nickname("test" + index + "nickname")
        .password("test")
        .phoneNumber("0122345678")
        .build());
  }

  public String getJwtToken(int index) throws Exception {
    Member member = joinMember(index);
    LoginRequestDto loginDto = new LoginRequestDto();
    loginDto.setEmail(member.getEmail());
    loginDto.setPassword(member.getPassword());

    ResultActions resultActions = mockMvc.perform(post("/api/auth")
        .content(gson.toJson(loginDto))
        .contentType(MediaType.APPLICATION_JSON));

    return resultActions.andReturn().getResponse().getHeader("refreshToken");
  }
}
