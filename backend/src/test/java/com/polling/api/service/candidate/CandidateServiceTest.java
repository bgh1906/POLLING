package com.polling.api.service.candidate;

import com.polling.api.controller.candidate.dto.CommentDto;
import com.polling.api.controller.candidate.dto.request.PatchCommentRequestDto;
import com.polling.api.controller.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.api.controller.candidate.dto.request.SaveCommentRequestDto;
import com.polling.api.controller.candidate.dto.response.FindProfileResponseDto;
import com.polling.api.controller.candidate.dto.response.FindVoteHistoryResponseDto;
import com.polling.api.queryrepository.CommentQueryRepository;
import com.polling.core.entity.candidate.Candidate;
import com.polling.core.entity.candidate.CandidateHistory;
import com.polling.core.entity.comment.Comment;
import com.polling.core.entity.member.Member;
import com.polling.core.entity.vote.Vote;
import com.polling.core.repository.candidate.CandidateHistoryRepository;
import com.polling.core.repository.candidate.CandidateRepository;
import com.polling.core.repository.comment.CommentRepository;
import com.polling.core.repository.member.MemberRepository;
import com.polling.core.repository.vote.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    CandidateHistoryRepository candidateHistoryRepository;


    @Autowired
    MemberRepository memberRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    CommentRepository commentRepository;

    private final String hostEmail = "polling@test.com";

    @BeforeEach
    void setUp() {
        Member admin = Member.builder()
                .nickname("관리자")
                .email(hostEmail)
                .password("sasds")
                .phoneNumber("01099123127")
                .build();
        memberRepository.save(admin);

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

//    @Test
//    void getProfile() {
//        //todo 오류 수정
//        Candidate candidate = candidateRepository.findByName("구아").get();
//        Member member = Member.builder()
//                .email("test@asd.com")
//                .nickname("test")
//                .password("asdasd")
//                .phoneNumber("01062341233")
//                .build();
//        memberRepository.save(member);
//        Comment comment = Comment.builder()
//                .candidate(candidate)
//                .content("화이팅")
//                .member(member)
//                .build();
//        commentRepository.save(comment);
//
//        FindProfileResponseDto responseDto = candidateService.getProfile(candidate.getId());
//
//        assertEquals("구아", responseDto.getName());
//        assertEquals("화이팅", responseDto.getComments().get(0).getContent());
//    }

//    @DisplayName("후보자가 표를 받은 이력을 가져옵니다. 해당 테스트에서 추가된 표는 집계되지 않습니다.")
//    @Test
//    void getHistory() {
//        //todo : 오류 수정
//        Candidate candidate = candidateRepository.findByName("구아").get();
//        Member member = Member.builder()
//                .email("test@asd.com")
//                .nickname("test")
//                .password("asdasd")
//                .phoneNumber("01062341233")
//                .build();
//        memberRepository.save(member);
//        CandidateHistory history = CandidateHistory.builder()
//                .member(member)
//                .voteCount(10)
//                .candidate(candidate)
//                .transactionId("transssss")
//                .build();
//        candidateHistoryRepository.save(history);
//
//        //when
//        List<FindVoteHistoryResponseDto> list = candidateService.getHistory(candidate.getId());
//
//        //then
//        Candidate savedCandidate = candidateRepository.findByName("구아").get();
//        assertEquals(1, list.size());
//    }

    @DisplayName("후보자에게 표를 던지고, transaction id를 저장합니다.")
    @Test
    void saveVoteHistory() {
        Candidate candidate = candidateRepository.findByName("구아").get();
        Member member = Member.builder()
                .email("test@asd.com")
                .nickname("test")
                .password("asdasd")
                .phoneNumber("01062341233")
                .build();
        memberRepository.save(member);
        SaveCandidateHistoryRequestDto requestDto = SaveCandidateHistoryRequestDto.builder()
                .candidateId(candidate.getId())
                .transactionId("asdsadas")
                .voteCount(10)
                .build();

        //when
        candidateService.saveVoteHistory(requestDto, member.getNickname());

        //then
        Candidate savedCandidate = candidateRepository.findByName("구아").get();
        assertEquals(10, savedCandidate.getVoteTotal());
    }

    @Test
    void saveComment() {
        //given
        Candidate candidate = candidateRepository.findByName("구아").get();
        Member member = Member.builder()
                .email("test@asd.com")
                .nickname("test")
                .password("asdasd")
                .phoneNumber("01062341233")
                .build();
        memberRepository.save(member);
        String content = "화이링";
        SaveCommentRequestDto requestDto = new SaveCommentRequestDto(candidate.getId(), content);

        //when
        Long id = candidateService.saveComment(requestDto, member.getNickname());

        //then
        Comment comment = commentRepository.findById(id).get();
        assertEquals(content, comment.getContent());

    }

    @Test
    void updateComment() {
        //given
        Candidate candidate = candidateRepository.findByName("구아").get();
        Member member = Member.builder()
                .email("test@asd.com")
                .nickname("test")
                .password("asdasd")
                .phoneNumber("01062341233")
                .build();
        memberRepository.save(member);
        Comment comment = Comment.builder()
                .candidate(candidate)
                .member(member)
                .content("화이링")
                .build();
        Comment saved = commentRepository.save(comment);
        String newContent = "더화이링";
        PatchCommentRequestDto requestDto = new PatchCommentRequestDto(newContent);

        //when
        candidateService.updateComment(saved.getId(), requestDto, member.getNickname());

        //then
        Comment updated = commentRepository.getById(saved.getId());
        assertEquals(newContent, updated.getContent());
    }

//    @Test
//    void deleteComment() {
//        //todo 오류 수정
//        Candidate candidate = candidateRepository.findByName("구아").get();
//        Member member = Member.builder()
//                .email("test@asd.com")
//                .nickname("test")
//                .password("asdasd")
//                .phoneNumber("01062341233")
//                .build();
//        memberRepository.save(member);
//        String content = "화이링";
//        SaveCommentRequestDto requestDto = new SaveCommentRequestDto(candidate.getId(), content);
//        Long id = candidateService.saveComment(requestDto, member.getNickname());
//
//        candidateService.deleteComment(id, member.getNickname());
//
//        //then
//        List<CommentDto> list = candidateService.getProfile(candidate.getId()).getComments();
//        assertEquals(0, list.size());
//    }
}