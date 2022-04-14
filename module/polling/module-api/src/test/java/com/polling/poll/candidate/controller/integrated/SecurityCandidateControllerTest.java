package com.polling.poll.candidate.controller.integrated;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.polling.auth.dto.request.LoginRequestDto;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.entity.status.MemberRole;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.poll.candidate.repository.CandidateRepository;
import com.polling.poll.candidate.service.CandidateService;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityCandidateControllerTest {

  @MockBean
  private CandidateService candidateService;

  @MockBean
  private CandidateRepository candidateRepository;

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
  public void 후보자에게투표_후보자찾기실패() throws Exception {
    //given
    final String url = "/api/polls/candidates";
    final SaveCandidateHistoryRequestDto requestDto = new SaveCandidateHistoryRequestDto(
        1L,
        "transaction_id",
        1);
    doThrow(new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND))
        .when(candidateService)
        .saveVoteHistory(any(SaveCandidateHistoryRequestDto.class), anyLong());

    //when
    ResultActions resultActions = mockMvc.perform(post(url)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().is4xxClientError());
  }

  @Test
  public void 후보자에게투표_성공() throws Exception {
    //given
    final String url = "/api/polls/candidates";
    final SaveCandidateHistoryRequestDto requestDto = new SaveCandidateHistoryRequestDto(
        1L,
        "transaction_id",
        1);

    //when
    ResultActions resultActions = mockMvc.perform(post(url)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().isOk());
    verify(candidateService, times(1)).saveVoteHistory(
        any(SaveCandidateHistoryRequestDto.class),
        anyLong());

  }

  public Member joinMember(int index) {
    Member member = Member
        .builder()
        .email("test" + index + "@email.com")
        .nickname("test" + index + "nickname")
        .password("test")
        .phoneNumber("0122345678")
        .build();
    member.addRole(MemberRole.ROLE_ADMIN);
    return memberRepository.save(member);
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
