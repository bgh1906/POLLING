package com.polling.candidate.service;


import com.polling.candidate.dto.CommentDto;
import com.polling.candidate.dto.request.ModifyCandidateRequestDto;
import com.polling.candidate.dto.request.ModifyCommentRequestDto;
import com.polling.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.candidate.dto.request.SaveCommentRequestDto;
import com.polling.candidate.dto.response.FindProfileResponseDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.candidate.CandidateHistory;
import com.polling.entity.comment.Comment;
import com.polling.entity.member.Member;
import com.polling.entity.poll.status.PollStatus;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import com.polling.queryrepository.CommentQueryRepository;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.comment.CommentRepository;
import com.polling.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final CommentRepository commentRepository;
    private final CommentQueryRepository commentQueryRepository;
    private final CandidateHistoryQueryRepository candidateHistoryQueryRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public FindProfileResponseDto getProfile(Long id){
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND));
        List<CommentDto> comments = commentQueryRepository.findAllByCandidateId(id);
        return FindProfileResponseDto.of(candidate, comments);
    }

    public void voteToCandidate(SaveCandidateHistoryRequestDto requestDto, Long memberId){

        if(requestDto.getVoteCount() <= 0) throw new CustomException(CustomErrorResult.INVALID_VOTES);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(CustomErrorResult.USER_NOT_FOUND));

        Candidate candidate = candidateRepository.findById(requestDto.getCandidateId())
                .orElseThrow(() -> new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND));

        Long id = candidate.getPoll().getId();

        if(candidateHistoryQueryRepository
                .existsByMemberIdAndPollIdInToday(memberId, id, LocalDate.now().atStartOfDay()))
            throw new CustomException(CustomErrorResult.ALREADY_VOTES);

        //todo : gRPC로 다른 api 호출 후 transaction id 받아오는 로직

        CandidateHistory.builder()
                .voteCount(requestDto.getVoteCount())
                .candidate(candidate)
                .member(member)
                .build();
    }

    public void saveComment(SaveCommentRequestDto requestDto, Long userId){
        Member member = memberRepository.findById(userId)
                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));
        Candidate candidate = candidateRepository.findById(requestDto.getCandidateId())
                .orElseThrow(()-> new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND));
        Comment comment = Comment.builder()
                .member(member)
                .content(requestDto.getContent())
                .candidate(candidate)
                .build();
        commentRepository.save(comment);
    }

    public void updateComment(Long commentId, ModifyCommentRequestDto requestDto, Long memberId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new CustomException(CustomErrorResult.COMMENT_NOT_FOUND));
        if(commentId.equals(memberId))
            throw new CustomException(CustomErrorResult.INVALID_COMMENT_OWNER);
        comment.updateContent(requestDto.getContent());
    }

    public void deleteComment(Long commentId, Long memberId){
        if(commentId.equals(memberId))
            throw new CustomException(CustomErrorResult.INVALID_COMMENT_OWNER);
        Comment comment = commentRepository
                .findById(commentId).orElseThrow(() -> new CustomException(CustomErrorResult.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
    }

    public void modifyCandidate(Long id, ModifyCandidateRequestDto requestDto) {
        Candidate candidate = candidateRepository
                .findById(id).orElseThrow(() -> new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND));
        PollStatus pollStatus = candidate.getPoll().getPollStatus();
        if(pollStatus == PollStatus.IN_PROGRESS || pollStatus == PollStatus.DONE)
            throw new CustomException(CustomErrorResult.IMPOSSIBLE_STATUS_TO_MODIFY);

        List<String> imagePaths = new ArrayList<>();
        imagePaths.add(requestDto.getImagePath1());
        imagePaths.add(requestDto.getImagePath2());
        imagePaths.add(requestDto.getImagePath3());
        if(requestDto.getName() != null)
            candidate.changeName(requestDto.getName());
        if(requestDto.getProfile() != null)
            candidate.changeProfile(requestDto.getProfile());
        if(requestDto.getThumbnail() != null)
            candidate.changeThumbnail(requestDto.getThumbnail());
        candidate.changeImagePaths(imagePaths);
    }

    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepository
                .findById(id).orElseThrow(() -> new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND));
        candidateRepository.delete(candidate);
    }
}
