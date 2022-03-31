package com.polling.poll.controller.comment.integrated;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.polling.auth.dto.request.LoginRequestDto;
import com.polling.entity.member.Member;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.poll.dto.comment.request.ModifyCommentRequestDto;
import com.polling.poll.dto.comment.request.SaveCommentRequestDto;
import com.polling.poll.service.CommentService;
import com.polling.repository.member.MemberRepository;
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
public class SecurityCommentControllerTest {

  @MockBean
  private CommentService commentService;

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
  public void 응원댓글작성성공() throws Exception {
    //given
    final String url = "/api/polls/candidates/comments";
    final SaveCommentRequestDto requestDto = SaveCommentRequestDto.builder().build();

    //when
    ResultActions resultActions = mockMvc.perform(post(url)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().isOk());
    verify(commentService, times(1)).saveComment(any(SaveCommentRequestDto.class), anyLong());
  }

  @Test
  public void 응원댓글수정실패_댓글이없음() throws Exception {
    //given
    final String url = "/api/polls/candidates/comments/{commentId}";
    final Long commentId = 1L;
    final ModifyCommentRequestDto requestDto = new ModifyCommentRequestDto("content");
    doThrow(new CustomException(CustomErrorResult.COMMENT_NOT_FOUND))
        .when(commentService).changeContent(anyLong(), anyString());

    //when
    ResultActions resultActions = mockMvc.perform(put(url, commentId)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().is4xxClientError());
  }

  @Test
  public void 응원댓글수정성공() throws Exception {
    //given
    final String url = "/api/polls/candidates/comments/{commentId}";
    final Long commentId = 1L;
    final ModifyCommentRequestDto requestDto = new ModifyCommentRequestDto("content");

    //when
    ResultActions resultActions = mockMvc.perform(put(url, commentId)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
        .content(gson.toJson(requestDto))
        .contentType(MediaType.APPLICATION_JSON));

    //then
    resultActions.andExpect(status().isOk());
    verify(commentService, times(1)).changeContent(anyLong(), anyString());
  }

  @Test
  public void 응원댓글삭제실패_댓글이없음() throws Exception {
    //given
    final String url = "/api/polls/candidates/comments/{commentId}";
    final Long commentId = 1L;
    doThrow(new CustomException(CustomErrorResult.COMMENT_NOT_FOUND))
        .when(commentService).deleteComment(anyLong());

    //when
    ResultActions resultActions = mockMvc.perform(delete(url, commentId)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().is4xxClientError());
  }

  @Test
  public void 응원댓글삭제성공() throws Exception {
    //given
    final String url = "/api/polls/candidates/comments/{commentId}";
    final Long commentId = 1L;

    //when
    ResultActions resultActions = mockMvc.perform(delete(url, commentId)
        .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

    //then
    resultActions.andExpect(status().isOk());
    verify(commentService, times(1)).deleteComment(anyLong());
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
