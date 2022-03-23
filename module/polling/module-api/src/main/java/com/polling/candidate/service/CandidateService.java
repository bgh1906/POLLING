package com.polling.candidate.service;


import com.polling.candidate.dto.CommentDto;
import com.polling.candidate.dto.request.PatchCommentRequestDto;
import com.polling.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.candidate.dto.request.SaveCommentRequestDto;
import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.candidate.dto.response.FindPollHistoryResponseDto;
import com.polling.candidate.dto.response.FindProfileResponseDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.candidate.CandidateHistory;
import com.polling.entity.comment.Comment;
import com.polling.entity.member.Member;
import com.polling.entity.poll.Poll;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import com.polling.queryrepository.CommentQueryRepository;
import com.polling.repository.candidate.CandidateHistoryRepository;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.comment.CommentRepository;
import com.polling.repository.member.MemberRepository;
import com.polling.repository.poll.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private final PollRepository pollRepository;

    //전체 대회에 대한 후보자들 목록 전부 뽑아야 하는지->그러면 param으로 vote 받아야 함
    @Transactional(readOnly = true)
    public List<FindCandidateResponseDto> getList(){

        return null;
    }

    @Transactional(readOnly = true)
    public FindProfileResponseDto getProfile(Long id){
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND));
        List<CommentDto> comments = commentQueryRepository.findAllByCandidateId(id);
        return FindProfileResponseDto.of(candidate, comments);
    }

    /**
     * vote의 historyStatus에 따라 리턴하는 history의 갯수가 달라집니다.
     * SHOW_ALL: 전부 리턴
     * SHOW_RECENT: 최근 50개의 내역만 리턴
     */
    @Transactional(readOnly = true)
    public List<FindPollHistoryResponseDto> getHistory(Long id){
//        Poll poll = pollRepository.findById(id)
//                .orElseThrow(()-> new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
//
//        List<FindPollHistoryResponseDto> response;
//        if(poll.getShowStatus().equals(ShowStatus.SHOW_ALL)){
//           response = candidateHistoryQueryRepository.findCandidateHistoryById(id);
//        }else if(poll.getShowStatus().equals(ShowStatus.SHOW_RECENT)){
//            response = candidateHistoryQueryRepository.findVoteHistoryByCandidateIdLimit50(id);
//        }else{
//            throw new CustomException(CustomErrorResult.STATUS_NOT_FOUND);
//        }
//        return response;
        return null;
    }

    public void saveVoteHistory(SaveCandidateHistoryRequestDto requestDto, Long userId){

//        if(requestDto.getVoteCount() < 0){
//            throw new CustomException(CustomErrorResult.USE_YOUR_TICKET);
//        }
//
//        //해당 표수 투표 가능한지 확인
//        Member member = memberRepository.findById(userId)
//                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));
//        int remainTicket = 0;
//        if(member.getLastTicket().compareTo(LocalDate.now()) == 0){
//            remainTicket++;
//        }
//        remainTicket += member.getBonusTicket();
//        if(remainTicket > requestDto.getVoteCount()){
//            throw new CustomException(CustomErrorResult.NO_LEFT_TICKET);
//        }
//
//        //본 투표
//        Candidate candidate = candidateRepository.findById(requestDto.getCandidateId())
//                .orElseThrow(()-> new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND));
//        CandidateHistory candidateHistory = CandidateHistory.builder()
//                .candidate(candidate)
//                .transactionId(requestDto.getTransactionId())
//                .member(member)
//                .voteCount(requestDto.getVoteCount())
//                .build();
//        candidateHistoryRepository.save(candidateHistory);
//        candidate.addVoteTotal(requestDto.getVoteCount());
//
//        //티켓차감
//        member.setLastTicketToNow();
//        member.setBonusTicket(remainTicket-requestDto.getVoteCount()+1);

    }

    public Long saveComment(SaveCommentRequestDto requestDto, Long userId){
        Member member = memberRepository.findById(userId)
                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));
        Candidate candidate = candidateRepository.findById(requestDto.getCandidateId())
                .orElseThrow(()-> new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND));
        Comment comment = Comment.builder()
                .member(member)
                .content(requestDto.getContent())
                .candidate(candidate)
                .build();
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }

    public void updateComment(Long commentId, PatchCommentRequestDto requestDto, Long userId){
        Member member = memberRepository.findById(userId)
                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new CustomException(CustomErrorResult.COMMENT_NOT_FOUND));
        if(comment.getMember().getId() != member.getId()){
            throw new CustomException(CustomErrorResult.INVALID_COMMENT_OWNER);
        }
        comment.updateContent(requestDto.getContent());
    }

    public void deleteComment(Long commentId, Long userId){
        Member member = memberRepository.findById(userId)
                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new CustomException(CustomErrorResult.COMMENT_NOT_FOUND));
        if(comment.getMember().getId() != member.getId()){
            throw new CustomException(CustomErrorResult.INVALID_COMMENT_OWNER);
        }
        commentRepository.deleteById(commentId);
    }

}
