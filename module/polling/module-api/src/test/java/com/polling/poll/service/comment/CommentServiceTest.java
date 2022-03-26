package com.polling.poll.service.comment;

import com.polling.entity.candidate.Candidate;
import com.polling.entity.member.Member;
import com.polling.entity.poll.Poll;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.poll.dto.candidate.request.AddVoteCountRequestDto;
import com.polling.poll.service.CandidateService;
import com.polling.poll.service.CommentService;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @InjectMocks
    private CommentService target;

    @Test
    public void commentServiceIsNotNull() throws Exception{
        assertThat(target).isNotNull();
    }

}
