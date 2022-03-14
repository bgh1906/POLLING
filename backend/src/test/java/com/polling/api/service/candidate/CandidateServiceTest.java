package com.polling.api.service.candidate;

import com.polling.api.controller.candidate.dto.request.SaveCandidateRequestDto;
import com.polling.api.controller.candidate.dto.response.FindProfileResponseDto;
import com.polling.api.controller.vote.dto.request.SaveVoteRequestDto;
import com.polling.api.queryrepository.CommentQueryRepository;
import com.polling.core.entity.candidate.Candidate;
import com.polling.core.entity.comment.Comment;
import com.polling.core.entity.user.User;
import com.polling.core.entity.user.status.UserRole;
import com.polling.core.entity.vote.Vote;
import com.polling.core.repository.candidate.CandidateRepository;
import com.polling.core.repository.comment.CommentRepository;
import com.polling.core.repository.user.UserRepository;
import com.polling.core.repository.vote.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CandidateServiceTest {

    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    CommentQueryRepository commentQueryRepository;
    @Autowired
    CandidateService candidateService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    CommentRepository commentRepository;

    private final String hostEmail = "polling@test.com";

    @BeforeEach
    void setUp() {
        User admin = User.builder()
                .name("관리자")
                .email(hostEmail)
                .password("sasds")
                .userRole(UserRole.ROLE_ADMIN)
                .phoneNumber("01099123127")
                .build();
        userRepository.save(admin);

        Vote vote = Vote.builder()
                .content("소개")
                .endDate(LocalDateTime.MAX)
                .host(admin)
                .name("투표")
                .startDate(LocalDateTime.MAX)
                .build();
        Vote savedVote = voteRepository.save(vote);

        Candidate candidate1 = Candidate.builder()
                .profilePath("somewhere")
                .name("구아")
                .content("소개")
                .vote(savedVote)
                .build();
        Candidate candidate2 = Candidate.builder()
                .profilePath("somewhere")
                .name("십아")
                .content("소개")
                .vote(savedVote)
                .build();
        candidateRepository.save(candidate1);
        candidateRepository.save(candidate2);
    }

    @Test
    void getList() {
    }

    @Test
    void getProfile() {
        Candidate candidate = candidateRepository.findByName("구아").get();
        User user = User.builder()
                .userRole(UserRole.ROLE_USER)
                .email("test@asd.com")
                .name("test")
                .password("asdasd")
                .phoneNumber("01062341233")
                .build();
        userRepository.save(user);
        Comment comment = Comment.builder()
                .candidate(candidate)
                .content("화이팅")
                .user(user)
                .build();
        commentRepository.save(comment);

        FindProfileResponseDto responseDto = candidateService.getProfile(candidate.getId());

        assertEquals("구아", responseDto.getName());
        assertEquals("화이팅", responseDto.getComments().get(0).getContent());
    }

    @Test
    void getHistory() {
    }

    @Test
    void saveVoteHistory() {
    }

    @Test
    void saveComment() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteComment() {
    }
}