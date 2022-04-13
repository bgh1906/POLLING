package com.polling.contact.controller.integrated;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.polling.auth.dto.request.LoginRequestDto;
import com.polling.contact.dto.SaveContactRequestDto;
import com.polling.contact.repository.ContactQueryRepository;
import com.polling.contact.repository.ContactRepository;
import com.polling.contact.service.ContactService;
import com.polling.member.entity.Member;
import com.polling.member.entity.status.MemberRole;
import com.polling.member.repository.MemberRepository;
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
public class SecurityContactControllerTest {

  @MockBean
  private ContactService contactService;

  @MockBean
  private ContactQueryRepository contactQueryRepository;

  @MockBean
  private ContactRepository contactRepository;

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
  public void 문의작성성공() throws Exception {
    //given
    final String url = "/api/contact";
    final SaveContactRequestDto requestDto =
        new SaveContactRequestDto("기업", "title", "content", "email");

    //when
    ResultActions resultActions = mockMvc.perform(post(url, requestDto)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().isOk());
    verify(contactService, times(1)).save(
        any(SaveContactRequestDto.class),
        anyLong());

  }

  @Test
  public void 내문의전체조회_성공() throws Exception {
    //given
    final String url = "/api/contact";

    //when
    ResultActions resultActions = mockMvc.perform(get(url)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().isOk());
    verify(contactQueryRepository, times(1)).findByMemberId(
        anyLong());
  }

  @Test
  public void 관리자모든문의열람() throws Exception {
    //given
    final String url = "/api/contact/admin/{page}/{limit}";

    //when
    ResultActions resultActions = mockMvc.perform(get(url, 0, 50)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().isOk());
    verify(contactQueryRepository, times(1)).findPageOrderByCreateDate(0, 50);
  }

  @Test
  public void 문의삭제_성공() throws Exception {
    //given
    final String url = "/api/contact";

    //when
    ResultActions resultActions = mockMvc.perform(delete(url)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().isOk());
    verify(contactRepository, times(1)).deleteById(anyLong());
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
