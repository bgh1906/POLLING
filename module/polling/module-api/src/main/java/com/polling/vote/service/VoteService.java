package com.polling.vote.service;


import com.polling.candidate.dto.request.SaveCandidateRequestDto;
import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.member.MemberRepository;
import com.polling.repository.vote.VoteRepository;
import com.polling.vote.dto.response.FindVoteResponseDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.member.Member;
import com.polling.entity.vote.Vote;
import com.polling.entity.vote.status.VoteStatus;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.vote.dto.request.SaveVoteRequestDto;
import com.polling.queryrepository.VoteQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final MemberRepository memberRepository;
    private final VoteQueryRepository voteQueryRepository;
    private final CandidateRepository candidateRepository;

    public Long saveVote(SaveVoteRequestDto requestDto, String hostEmail){
        Member host = memberRepository.findByEmail(hostEmail)
                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));
        //save vote
        Vote vote = Vote.builder()
                .name(requestDto.getName())
                .host(host)
                .content(requestDto.getContent())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .historyStatus(requestDto.getHistoryStatus())
                .build();
        Vote savedVote = voteRepository.save(vote);

        //save candidate
        for (SaveCandidateRequestDto request:
                requestDto.getCandidates()) {
            Candidate candidate = request.toEntity();
            candidate.assignVote(savedVote);
            candidateRepository.save(candidate);
        }

        return savedVote.getId();
    }

    public FindVoteResponseDto getRanking(Long id){
        Vote vote = voteRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
        List<FindCandidateResponseDto> list = voteQueryRepository.findCandidatesSortByVoteTotal(id);
        return new FindVoteResponseDto(list, vote.getName(), vote.getContent(), vote.getStartDate(), vote.getEndDate());
    }

    public void delete(Long id){
        Vote vote = voteRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
        voteRepository.deleteById(id);
    }

    public void updateStatus(Long id, VoteStatus status){
        Vote vote = voteRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
        vote.setVoteStatus(status);
    }

}