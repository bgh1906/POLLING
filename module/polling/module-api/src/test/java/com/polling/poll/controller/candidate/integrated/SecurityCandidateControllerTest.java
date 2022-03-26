package com.polling.poll.controller.candidate.integrated;

import com.google.gson.Gson;
import com.polling.auth.dto.LoginDto;
import com.polling.entity.member.Member;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.poll.dto.candidate.request.AddVoteCountRequestDto;
import com.polling.poll.dto.candidate.request.ModifyCandidateRequestDto;
import com.polling.poll.dto.candidate.request.ModifyCommentRequestDto;
import com.polling.poll.dto.candidate.request.SaveCommentRequestDto;
import com.polling.poll.service.CandidateService;
import com.polling.repository.candidate.CandidateRepository;
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
    public void 후보자에게투표_후보자찾기실패() throws Exception{
        //given
        final String url = "/api/polls/candidates";
        final AddVoteCountRequestDto requestDto = AddVoteCountRequestDto.builder().build();
        doThrow(new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND))
                .when(candidateService).addVoteCount(any(AddVoteCountRequestDto.class), anyLong());

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
        final String url = "/api/polls/candidates";
        final AddVoteCountRequestDto requestDto = AddVoteCountRequestDto.builder().build();

        //when
        ResultActions resultActions = mockMvc.perform(post(url)
                .header(HttpHeaders.AUTHORIZATION, getJwtToken(1))
                .content(gson.toJson(requestDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk());
        verify(candidateService, times(1)).addVoteCount(any(AddVoteCountRequestDto.class), anyLong());

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
