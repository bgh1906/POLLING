package com.polling.api.service.candidate;

import com.polling.api.controller.candidate.dto.CommentDto;
import com.polling.api.controller.candidate.dto.request.PatchCommentRequestDto;
import com.polling.api.controller.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.api.controller.candidate.dto.request.SaveCommentRequestDto;
import com.polling.api.controller.candidate.dto.response.FindCandidateResponseDto;
import com.polling.api.controller.candidate.dto.response.FindProfileResponseDto;
import com.polling.api.controller.candidate.dto.response.FindVoteHistoryResponseDto;
import com.polling.api.controller.exception.CustomException;
import com.polling.api.controller.exception.ErrorCode;
import com.polling.api.queryrepository.CandidateHistoryQueryRepository;
import com.polling.api.queryrepository.CommentQueryRepository;
import com.polling.core.entity.candidate.Candidate;
import com.polling.core.entity.candidate.CandidateHistory;
import com.polling.core.entity.comment.Comment;
import com.polling.core.entity.member.Member;
import com.polling.core.repository.candidate.CandidateHistoryRepository;
import com.polling.core.repository.candidate.CandidateRepository;
import com.polling.core.repository.comment.CommentRepository;
import com.polling.core.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;
    private final CandidateHistoryRepository candidateHistoryRepository;
    private final CandidateHistoryQueryRepository candidateHistoryQueryRepository;
    private final MemberRepository memberRepository;

    //전체 대회에 대한 후보자들 목록 전부 뽑아야 하는지->그러면 param으로 vote 받아야 함
    @Transactional(readOnly = true)
    public List<FindCandidateResponseDto> getList(){

        return null;
    }

    @Transactional(readOnly = true)
    public FindProfileResponseDto getProfile(Long id){
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.CANDIDATE_NOT_FOUND));
        List<CommentDto> comments = commentQueryRepository.findCommentByCandidateId(id);
        return FindProfileResponseDto.of(candidate, comments);
    }

    @Transactional(readOnly = true)
    public List<FindVoteHistoryResponseDto> getHistory(Long id){
        List<FindVoteHistoryResponseDto> response = candidateHistoryQueryRepository.findVoteHistoryByCandidateId(id);
        return response;
    }

    public void saveVoteHistory(SaveCandidateHistoryRequestDto requestDto, String userName){
        Member member = memberRepository.findByNickname(userName)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        Candidate candidate = candidateRepository.findById(requestDto.getCandidateId())
                .orElseThrow(()-> new CustomException(ErrorCode.CANDIDATE_NOT_FOUND));
        CandidateHistory candidateHistory = CandidateHistory.builder()
                .candidate(candidate)
                .transactionId(requestDto.getTransactionId())
                .member(member)
                .voteCount(requestDto.getVoteCount())
                .build();
        candidateHistoryRepository.save(candidateHistory);
        candidate.addVote(requestDto.getVoteCount());
    }

    public Long saveComment(SaveCommentRequestDto requestDto, String userName){
        Member member = memberRepository.findByNickname(userName)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        Candidate candidate = candidateRepository.findById(requestDto.getCandidateId())
                .orElseThrow(()-> new CustomException(ErrorCode.CANDIDATE_NOT_FOUND));
        Comment comment = Comment.builder()
                .member(member)
                .content(requestDto.getContent())
                .candidate(candidate)
                .build();
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }

    public void updateComment(Long commentId, PatchCommentRequestDto requestDto, String userName){
        Member member = memberRepository.findByNickname(userName)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        if(comment.getMember().getId() != member.getId()){
            throw new CustomException(ErrorCode.INVALID_COMMENT_OWNER);
        }
        comment.updateContent(requestDto.getContent());
    }

    public void deleteComment(Long commentId, String userName){
        Member member = memberRepository.findByNickname(userName)
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        if(comment.getMember().getId() != member.getId()){
            throw new CustomException(ErrorCode.INVALID_COMMENT_OWNER);
        }
        commentRepository.deleteById(commentId);
    }



}
