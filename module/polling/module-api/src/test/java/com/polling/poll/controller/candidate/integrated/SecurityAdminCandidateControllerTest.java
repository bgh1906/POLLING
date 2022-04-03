package com.polling.poll.controller.candidate.integrated;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.polling.auth.dto.request.LoginRequestDto;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.dto.request.ModifyCandidateRequestDto;
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
public class SecurityAdminCandidateControllerTest {

  @MockBean
  private CandidateService candidateService;

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
  public void 후보자정보수정실패_투표가변경불가능한상태() throws Exception {
    //given
    final String url = "/api/polls/admin/candidates/{candidatesId}";
    final Long candidatesId = 1L;
    final ModifyCandidateRequestDto requestDto = new ModifyCandidateRequestDto("name",
        "profile",
        "image1",
        "image2",
        "image3",
        "thumbnail");
    doThrow(new CustomException(CustomErrorResult.IMPOSSIBLE_STATUS_TO_MODIFY))
        .when(candidateService)
        .modifyCandidate(anyLong(), any(ModifyCandidateRequestDto.class));

    //when
    ResultActions resultActions = mockMvc.perform(patch(url, candidatesId)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().is4xxClientError());
  }

  @Test
  public void 후보자정보수정성공() throws Exception {
    //given
    final String url = "/api/polls/admin/candidates/{candidatesId}";
    final Long candidatesId = 1L;
    final ModifyCandidateRequestDto requestDto = new ModifyCandidateRequestDto("name",
        "profile",
        "image1",
        "image2",
        "image3",
        "thumbnail");

    //when
    ResultActions resultActions = mockMvc.perform(put(url, candidatesId)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().isOk());
    verify(candidateService, times(1)).modifyCandidate(anyLong(),
        any(ModifyCandidateRequestDto.class));
  }

  @Test
  public void 후보자삭제실패_후보자가없음() throws Exception {
    //given
    final String url = "/api/polls/admin/candidates/{candidateId}";
    final Long candidateId = 1L;
    doThrow(new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND)).when(candidateService)
        .deleteCandidate(anyLong());

    //when
    ResultActions resultActions = mockMvc.perform(delete(url, candidateId)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().is4xxClientError());
  }

  @Test
  public void 후보자삭제성공() throws Exception {
    //given
    final String url = "/api/polls/admin/candidates/{candidateId}";
    final Long candidateId = 1L;

    //when
    ResultActions resultActions = mockMvc.perform(delete(url, candidateId)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().isOk());
    verify(candidateService, times(1)).deleteCandidate(candidateId);
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
