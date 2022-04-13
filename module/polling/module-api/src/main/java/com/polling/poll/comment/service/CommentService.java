package com.polling.poll.comment.service;


import com.polling.aop.annotation.Trace;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.repository.CandidateRepository;
import com.polling.poll.comment.dto.request.SaveCommentRequestDto;
import com.polling.poll.comment.entity.Comment;
import com.polling.poll.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

  private final CandidateRepository candidateRepository;
  private final CommentRepository commentRepository;
  private final MemberRepository memberRepository;

  @Trace
  public void saveComment(SaveCommentRequestDto requestDto, Long memberId) {
    Member member = getMember(memberId);
    Candidate candidate = getCandidate(requestDto.getCandidateId());
    Comment comment = Comment.builder()
        .member(member)
        .content(requestDto.getContent())
        .candidate(candidate)
        .build();
    commentRepository.save(comment);
  }

  @Trace
  public void changeContent(Long memberId, Long commentId, String content) {
    Member member = getMember(memberId);
    Comment comment = getComment(commentId);
    if (comment.getMember() != member) {
      throw new CustomException(CustomErrorResult.UNAUTHORIZED_MEMBER);
    }
    comment.updateContent(content);
  }

  @Trace
  public void deleteComment(Long memberId, Long commentId) {
    Member member = getMember(memberId);
    Comment comment = getComment(commentId);
    if (comment.getMember() != member) {
      throw new CustomException(CustomErrorResult.UNAUTHORIZED_MEMBER);
    }
    commentRepository.delete(comment);
  }

  private Comment getComment(Long commentId) {
    return commentRepository
        .findById(commentId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.COMMENT_NOT_FOUND));
  }

  private Member getMember(Long memberId) {
    return memberRepository.findById(memberId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.USER_NOT_FOUND));
  }

  private Candidate getCandidate(Long candidateId) {
    return candidateRepository.findById(candidateId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND));
  }
}
