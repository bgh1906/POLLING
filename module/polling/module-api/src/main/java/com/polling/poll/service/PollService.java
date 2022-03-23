package com.polling.poll.service;


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
import com.polling.repository.member.MemberRepository;
import com.polling.repository.poll.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class PollService {
    private final PollRepository pollRepository;
    private final MemberRepository memberRepository;
    private final PollQueryRepository pollQueryRepository;

    public void savePoll(SavePollRequestDto requestDto, String pollCreatorEmail){
        Member pollCreator = memberRepository.findByEmail(pollCreatorEmail)
                .orElseThrow(()->new CustomException(CustomErrorResult.USER_NOT_FOUND));

        // 투표 생성
        Poll poll = pollRepository.save(Poll.builder()
                .pollCreator(pollCreator)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .build());

        // 후보자 추가
        requestDto.getCandidateDtos().forEach(candidateDto -> {
                    poll.addCandidate(Candidate.builder()
                            .imagePaths(candidateDto.getImagePaths())
                            .name(candidateDto.getName())
                            .profile(candidateDto.getProfile())
                            .build());
                    });
    }

    @Transactional(readOnly = true)
    public FindPollResponseDto getRanking(Long id){
        Poll poll = pollRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
        List<FindCandidateResponseDto> list = pollQueryRepository.findCandidatesSortByVoteTotal(id);
        return new FindPollResponseDto(list, poll.getTitle(), poll.getContent(), poll.getStartDate(), poll.getEndDate());
    }

    public void delete(Long id){
        Poll poll = pollRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
        pollRepository.delete(poll);
    }

    public void updateStatus(Long id, String status){
        PollStatus pollStatus = PollStatus.findStatusByName(status);
        Poll poll = pollRepository.findById(id)
                .orElseThrow(()->new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
        poll.changePollStatus(pollStatus);
    }

}