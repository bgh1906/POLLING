package com.polling.candidate.service;

import com.polling.poll.service.PollService;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.member.MemberRepository;
import com.polling.repository.poll.PollRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {
    @InjectMocks
    private CandidateService target;

    @Mock
    private CandidateRepository candidateRepository;

    @Test
    public void candidateServiceIsNotNull() throws Exception{
        assertThat(target).isNotNull();
    }

    @Test
    public void 후보자정보조회() throws Exception{
        //given


        //when

        //then
    }

}
