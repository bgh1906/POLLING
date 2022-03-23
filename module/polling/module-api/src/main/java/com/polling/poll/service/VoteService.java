package com.polling.poll.service;


import com.polling.candidate.dto.request.SaveCandidateRequestDto;
import com.polling.candidate.dto.response.FindCandidateResponseDto;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.member.Member;
import com.polling.entity.poll.Poll;
import com.polling.entity.poll.status.PollStatus;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.poll.dto.request.SavePollRequestDto;
import com.polling.poll.dto.response.FindPollResponseDto;
import com.polling.queryrepository.PollQueryRepository;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.member.MemberRepository;
import com.polling.repository.poll.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class VoteService {
    private final PollRepository pollRepository;
    private final MemberRepository memberRepository;
    private final PollQueryRepository pollQueryRepository;
    private final CandidateRepository candidateRepository;

    public Long saveVote(SavePollRequestDto requestDto, String hostEmail){
        Member host = memberRepository.findByEmail(hostEmail)
                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));
        //save vote
        Poll poll = Poll.builder()
                .title(requestDto.getName())
                .host(host)
                .content(requestDto.getContent())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .showStatus(requestDto.getShowStatus())
                .build();
        Poll savedPoll = pollRepository.save(poll);

        //save candidate
        for (SaveCandidateRequestDto request:
                requestDto.getCandidates()) {
            Candidate candidate = request.toEntity();
            candidate.assignVote(savedPoll);
            candidateRepository.save(candidate);
        }

        return savedPoll.getId();
    }

    public FindPollResponseDto getRanking(Long id){
        Poll poll = pollRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
        List<FindCandidateResponseDto> list = pollQueryRepository.findCandidatesSortByVoteTotal(id);
        return new FindPollResponseDto(list, poll.getTitle(), poll.getContent(), poll.getStartDate(), poll.getEndDate());
    }

    public void delete(Long id){
        Poll poll = pollRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
        pollRepository.deleteById(id);
    }

    public void updateStatus(Long id, PollStatus status){
        Poll poll = pollRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
        poll.changePollStatus(status);
    }

}