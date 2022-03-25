package com.polling.candidate.controller.integrated;

import com.google.gson.Gson;
import com.polling.auth.dto.LoginDto;
import com.polling.candidate.dto.request.ModifyCandidateRequestDto;
import com.polling.candidate.dto.request.ModifyCommentRequestDto;
import com.polling.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.candidate.dto.request.SaveCommentRequestDto;
import com.polling.candidate.service.CandidateService;
import com.polling.entity.member.Member;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.service.MemberService;
import com.polling.poll.dto.request.SavePollRequestDto;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityCandidateControllerTest {
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
    public void 후보자에게투표_후보자찾기실패() throws Exception{
        //given
        final String url = "/api/candidates";
        final SaveCandidateHistoryRequestDto requestDto = SaveCandidateHistoryRequestDto.builder().build();
        doThrow(new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND))
                .when(candidateService).voteToCandidate(any(SaveCandidateHistoryRequestDto.class), anyLong());

        //when
        ResultActions resultActions = mockMvc.perform(post(url)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
                .content(gson.toJson(requestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void 후보자에게투표_성공() throws Exception{
        //given
        final String url = "/api/candidates";
        final SaveCandidateHistoryRequestDto requestDto = SaveCandidateHistoryRequestDto.builder().build();

        //when
        ResultActions resultActions = mockMvc.perform(post(url)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
                .content(gson.toJson(requestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk());
        verify(candidateService, times(1)).voteToCandidate(any(SaveCandidateHistoryRequestDto.class), anyLong());

    }

    @Test
    public void 후보자정보수정실패_투표가변경불가능한상태() throws Exception{
        //given
        final String url = "/api/candidates/{candidatesId}";
        final Long candidatesId = 1L;
        final ModifyCandidateRequestDto requestDto = new ModifyCandidateRequestDto("name",
                        "profile",
                        "image1",
                        "image2",
                        "image3",
                        "thumbnail");
        doThrow(new CustomException(CustomErrorResult.IMPOSSIBLE_STATUS_TO_MODIFY))
                .when(candidateService).modifyCandidate(anyLong(), any(ModifyCandidateRequestDto.class));

        //when
        ResultActions resultActions = mockMvc.perform(patch(url, candidatesId)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
                .content(gson.toJson(requestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void 후보자정보수정성공() throws Exception{
        //given
        final String url = "/api/candidates/{candidatesId}";
        final Long candidatesId = 1L;
        final ModifyCandidateRequestDto requestDto = new ModifyCandidateRequestDto("name",
                "profile",
                "image1",
                "image2",
                "image3",
                "thumbnail");

        //when
        ResultActions resultActions = mockMvc.perform(patch(url, candidatesId)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
                .content(gson.toJson(requestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk());
        verify(candidateService, times(1)).modifyCandidate(anyLong(), any(ModifyCandidateRequestDto.class));
    }

    @Test
    public void 후보자삭제실패_후보자가없음() throws Exception{
        //given
        final String url = "/api/candidates/{candidatesId}";
        final Long candidatesId = 1L;
        doThrow(new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND))
                .when(candidateService).deleteCandidate(anyLong());

        //when
        ResultActions resultActions = mockMvc.perform(delete(url, candidatesId)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void 후보자삭제성공() throws Exception{
        //given
        final String url = "/api/candidates/{candidatesId}";
        final Long candidatesId = 1L;

        //when
        ResultActions resultActions = mockMvc.perform(delete(url, candidatesId)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

        //then
        resultActions.andExpect(status().isOk());
        verify(candidateService, times(1)).deleteCandidate(candidatesId);
    }
    
    @Test
    public void 응원댓글작성실패_후보자가없음() throws Exception{
        //given
        final String url = "/api/candidates/comment";
        final SaveCommentRequestDto requestDto = SaveCommentRequestDto.builder().build();
        doThrow(new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND))
                .when(candidateService).saveComment(any(SaveCommentRequestDto.class), anyLong());

        //when
        ResultActions resultActions = mockMvc.perform(post(url)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
                .content(gson.toJson(requestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void 응원댓글작성성공() throws Exception{
        //given
        final String url = "/api/candidates/comment";
        final SaveCommentRequestDto requestDto = SaveCommentRequestDto.builder().build();

        //when
        ResultActions resultActions = mockMvc.perform(post(url)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
                .content(gson.toJson(requestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk());
        verify(candidateService, times(1)).saveComment(any(SaveCommentRequestDto.class), anyLong());
    }

    @Test
    public void 응원댓글수정실패_댓글이없음() throws Exception{
        //given
        final String url = "/api/candidates/comment/{commentId}";
        final Long commentId = 1L;
        final ModifyCommentRequestDto requestDto = new ModifyCommentRequestDto("content");
        doThrow(new CustomException(CustomErrorResult.COMMENT_NOT_FOUND))
                .when(candidateService).updateComment(anyLong(), any(ModifyCommentRequestDto.class), anyLong());

        //when
        ResultActions resultActions = mockMvc.perform(put(url, commentId)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
                .content(gson.toJson(requestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void 응원댓글수정성공() throws Exception{
        //given
        final String url = "/api/candidates/comment/{commentId}";
        final Long commentId = 1L;
        final ModifyCommentRequestDto requestDto = new ModifyCommentRequestDto("content");

        //when
        ResultActions resultActions = mockMvc.perform(put(url, commentId)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
                .content(gson.toJson(requestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk());
        verify(candidateService, times(1)).updateComment(anyLong(), any(ModifyCommentRequestDto.class), anyLong());
    }

    @Test
    public void 응원댓글삭제실패_댓글이없음() throws Exception{
        //given
        final String url = "/api/candidates/comment/{commentId}";
        final Long commentId = 1L;
        doThrow(new CustomException(CustomErrorResult.COMMENT_NOT_FOUND))
                .when(candidateService).deleteComment(anyLong(), anyLong());

        //when
        ResultActions resultActions = mockMvc.perform(delete(url, commentId)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

        //then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void 응원댓글삭제성공() throws Exception{
        //given
        final String url = "/api/candidates/comment/{commentId}";
        final Long commentId = 1L;

        //when
        ResultActions resultActions = mockMvc.perform(delete(url, commentId)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1)));

        //then
        resultActions.andExpect(status().isOk());
        verify(candidateService, times(1)).deleteComment(anyLong(), anyLong());
    }



    public Member joinMember(int index){
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
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(member.getEmail());
        loginDto.setPassword(member.getPassword());

        ResultActions resultActions = mockMvc.perform(post("/api/auth")
                .content(gson.toJson(loginDto))
                .contentType(MediaType.APPLICATION_JSON));

        return resultActions.andReturn().getResponse().getHeader("refreshToken");
    }
}
