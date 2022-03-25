package com.polling.candidate.queryrepository;

import com.polling.candidate.dto.CommentDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.comment.Comment;
import com.polling.entity.member.Member;
import com.polling.queryrepository.CommentQueryRepository;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.comment.CommentRepository;
import com.polling.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class CommentQueryRepositoryTest {
    @Autowired
    private CommentQueryRepository commentQueryRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void 후보에작성된댓글모두조회() throws Exception{
        //given
        Member savedMember = memberRepository.save(Member.builder()
                .email("test")
                .nickname("testName")
                .build());
        Candidate savedCandidate = candidateRepository.save(Candidate.builder()
                .name("suzy")
                .profile("hello")
                .build());
        createComment(savedMember, savedCandidate, "hello1");
        createComment(savedMember, savedCandidate, "hello2");
        createComment(savedMember, savedCandidate, "hello3");
        createComment(savedMember, savedCandidate, "hello4");
        createComment(savedMember, savedCandidate, "hello5");

        //when
        List<CommentDto> commentDtos = commentQueryRepository.findAllByCandidateId(savedCandidate.getId());

        //then
        assertThat(commentDtos.size()).isEqualTo(5);
        assertThat(commentDtos.get(0).getContent()).isEqualTo("hello1");
        assertThat(commentDtos.get(0).getUserId()).isEqualTo(savedMember.getId());
        assertThat(commentDtos.get(0).getUserName()).isEqualTo("testName");
    }

    private Comment createComment(Member member, Candidate target, String message){
        return commentRepository.save(Comment.builder()
                .content(message)
                .candidate(target)
                .member(member)
                .build());
    }
}
