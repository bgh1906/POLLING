package com.ssafy.api.service.vote;

import com.ssafy.api.controller.candidate.dto.request.SaveCandidateRequestDto;
import com.ssafy.api.controller.candidate.dto.response.FindCandidateResponseDto;
import com.ssafy.api.controller.exception.CustomException;
import com.ssafy.api.controller.exception.ErrorCode;
import com.ssafy.api.controller.vote.dto.request.SaveVoteRequestDto;
import com.ssafy.api.controller.vote.dto.response.FindVoteResponseDto;
import com.ssafy.api.queryrepository.VoteQueryRepository;
import com.ssafy.core.entity.candidate.Candidate;
import com.ssafy.core.entity.user.User;
import com.ssafy.core.entity.vote.Vote;
import com.ssafy.core.repository.candidate.CandidateRepository;
import com.ssafy.core.repository.user.UserRepository;
import com.ssafy.core.repository.vote.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final VoteQueryRepository voteQueryRepository;
    private final CandidateRepository candidateRepository;

    public Long saveVote(SaveVoteRequestDto requestDto){

        //save vote
        Vote vote = Vote.builder()
                .name(requestDto.getName())
//                .host(admin)
                .content(requestDto.getContent())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
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
                .orElseThrow(()->new RuntimeException());
        List<FindCandidateResponseDto> list = voteQueryRepository.findCandidatesSortByVoteTotal(id);
        FindVoteResponseDto responseDto = new FindVoteResponseDto(list, vote.getName(), vote.getContent(), vote.getStartDate(), vote.getEndDate());
        return responseDto;
    }

    public void voteTo(Long candidateId, int vote){
        Candidate candidate = candidateRepository.findById(candidateId).get();
        candidate.addVote(vote);
    }
}
