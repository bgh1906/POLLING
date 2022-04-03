package com.polling.member.controller.integrated;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.polling.auth.dto.request.LoginRequestDto;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import com.polling.member.service.MemberService;
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
public class SecurityMemberControllerTest {

  @MockBean
  private MemberService memberService;

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
  public void 닉네임수정실패_닉네임중복() throws Exception {
    //given
    final String url = "/api/members/nickname/{nickname}";
    final String nickname = "testNickname";
    doThrow(new CustomException(CustomErrorResult.DUPLICATE_NICKNAME))
        .when(memberService)
        .changeNickname(any(Long.class), eq(nickname));

    //when
    ResultActions resultActions = mockMvc.perform(patch(url, nickname)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().is4xxClientError());
  }

  @Test
  public void 닉네임수정성공() throws Exception {
    //given
    final String url = "/api/members/nickname/{nickname}";
    final String nickname = "testNickname";

    //when
    ResultActions resultActions = mockMvc.perform(patch(url, nickname)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().isOk());
    verify(memberService, times(1)).changeNickname(any(Long.class), eq(nickname));
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
