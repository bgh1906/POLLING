package com.polling.api.service.vote;

import com.polling.api.controller.candidate.dto.request.SaveCandidateRequestDto;
import com.polling.api.controller.candidate.dto.response.FindCandidateResponseDto;
import com.polling.api.controller.vote.dto.request.SaveVoteRequestDto;
import com.polling.api.controller.vote.dto.response.FindVoteResponseDto;
import com.polling.api.queryrepository.VoteQueryRepository;
import com.polling.core.entity.candidate.Candidate;
import com.polling.core.entity.user.User;
import com.polling.core.entity.vote.Vote;
import com.polling.core.entity.vote.status.VoteStatus;
import com.polling.core.repository.candidate.CandidateRepository;
import com.polling.core.repository.user.UserRepository;
import com.polling.core.repository.vote.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final VoteQueryRepository voteQueryRepository;
    private final CandidateRepository candidateRepository;

    public Long saveVote(SaveVoteRequestDto requestDto, String hostEmail){
        User host = userRepository.findByEmail(hostEmail).orElseThrow(RuntimeException::new);
        //save vote
        Vote vote = Vote.builder()
                .name(requestDto.getName())
                .host(host)
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
        Vote vote = voteRepository.findById(id).orElseThrow(RuntimeException::new);
        List<FindCandidateResponseDto> list = voteQueryRepository.findCandidatesSortByVoteTotal(id);
        return new FindVoteResponseDto(list, vote.getName(), vote.getContent(), vote.getStartDate(), vote.getEndDate());
    }

    public void delete(Long id){
        Vote vote = voteRepository.findById(id).orElseThrow(RuntimeException::new);
        voteRepository.deleteById(id);
    }

    public void endVote(Long id){
        Vote vote = voteRepository.findById(id).orElseThrow(RuntimeException::new);
        vote.setVoteStatus(VoteStatus.COMPLETE);
    }

    public void voteTo(Long candidateId, int vote){
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(RuntimeException::new);
        candidate.addVote(vote);
    }
}