package com.polling.poll.service;


import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.member.Member;
import com.polling.entity.member.status.MemberRole;
import com.polling.entity.poll.Poll;
import com.polling.entity.poll.status.PollStatus;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.poll.dto.candidate.request.SaveCandidateRequestDto;
import com.polling.poll.dto.candidate.response.FindAdminCandidateResponseDto;
import com.polling.poll.dto.candidate.response.FindAnonymousCandidateResponseDto;
import com.polling.poll.dto.request.ModifyPollRequestDto;
import com.polling.poll.dto.request.SavePollRequestDto;
import com.polling.poll.dto.response.FindPollWithCandidateResponseDto;
import com.polling.poll.dto.response.FindSimplePollResponseDto;
import com.polling.queryrepository.CandidateQueryRepository;
import com.polling.queryrepository.PollQueryRepository;
import com.polling.repository.member.MemberRepository;
import com.polling.repository.poll.PollRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class PollService {

  private final PollRepository pollRepository;
  private final MemberRepository memberRepository;
  private final CandidateQueryRepository candidateQueryRepository;
  private final PollQueryRepository pollQueryRepository;

  @Trace
  public void savePoll(SavePollRequestDto requestDto, Long pollCreatorId) {
    Member pollCreator = memberRepository.findById(pollCreatorId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.USER_NOT_FOUND));

    validateRole(pollCreator.getMemberRole());

    // 투표 생성
    Poll poll = pollRepository.save(requestDto.toPollEntity());

    // 후보자 추가
    requestDto.getCandidateDtos().forEach(candidateDto -> {
      poll.addCandidate(candidateDto.toEntity());
    });
  }

  @Retry
  @Transactional(readOnly = true)
  public FindSimplePollResponseDto findPollThumbnail(Long pollId) {
    Poll poll = getPoll(pollId);
    List<FindAnonymousCandidateResponseDto> list = candidateQueryRepository.findAllSimpleByPollId(
        pollId);
    return FindSimplePollResponseDto.of(list, poll);
  }

  @Trace
  @Retry
  @Transactional(readOnly = true)
  public FindPollWithCandidateResponseDto findPollAllInfo(Long pollId) {
    Poll poll = getPoll(pollId);
    List<Candidate> candidates = candidateQueryRepository.findAllByPollId(pollId);
    List<FindAdminCandidateResponseDto> list = candidates.stream()
        .map(candidate -> FindAdminCandidateResponseDto.builder()
            .candidateId(candidate.getId())
            .name(candidate.getName())
            .thumbnail(candidate.getThumbnail())
            .galleries(candidate.getGalleries())
            .profile(candidate.getProfile())
            .build()).collect(Collectors.toList());
    return FindPollWithCandidateResponseDto.of(list, poll);
  }

  @Trace
  public void deletePoll(Long pollId) {
    if (!pollRepository.existsById(pollId)) {
      throw new CustomException(CustomErrorResult.VOTE_NOT_FOUND);
    }
    pollRepository.deleteById(pollId);
  }

  @Trace
  public void addCandidate(SaveCandidateRequestDto requestDto) {
    Poll poll = getPoll(requestDto.getCandidateIndex());
    poll.addCandidate(requestDto.toEntity());
  }

  @Trace
  public void modifyPoll(Long pollId, ModifyPollRequestDto requestDto) {
    Poll poll = getPoll(pollId);
    validateStatus(poll);
    if (requestDto.getTitle() != null && requestDto.getContent() != null) {
      poll.changeDescription(requestDto.getTitle(), requestDto.getContent());
    }
    if (requestDto.getStartDate() != null && requestDto.getEndDate() != null) {
      poll.changePeriod(requestDto.getStartDate(), requestDto.getEndDate());
    }
    if (requestDto.getThumbnail() != null) {
      poll.changeThumbnail(requestDto.getThumbnail());
    }
    if (requestDto.getOpenStatus() != null) {
      poll.changeOpenStatus(requestDto.getOpenStatus());
    }
  }

  @Trace
  public void modifyStatus(Long pollId, String status) {
    PollStatus pollStatus = PollStatus.findStatusByName(status);
    Poll poll = getPoll(pollId);
    validateStatus(poll);
    poll.changePollStatus(pollStatus);
  }

  @Scheduled(fixedRate = 60000)
  public void checkPollEndTime() {
    List<Poll> polls = pollQueryRepository.findByCurrentBeforeEndTime(LocalDateTime.now());
    if (!polls.isEmpty()) {
      polls.forEach(poll -> poll.changePollStatus(PollStatus.DONE));
    }
  }

  @Trace
  public void changeOpenStatus(Long pollId) {
    Poll poll = getPoll(pollId);
    poll.changeOpenStatus();
  }

  private Poll getPoll(Long pollId) {
    return pollRepository.findById(pollId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.VOTE_NOT_FOUND));
  }

  private void validateRole(Set<MemberRole> memberRole) {
    if (!(memberRole.contains(MemberRole.ROLE_COMPANY) || memberRole.contains(
        MemberRole.ROLE_ADMIN))) {
      throw new CustomException(CustomErrorResult.UNAUTHORIZED_MEMBER_ROLE);
    }
  }

  private void validateStatus(Poll poll) {
    if (poll.getPollStatus() == PollStatus.IN_PROGRESS
        || poll.getPollStatus() == PollStatus.DONE) {
      throw new CustomException(CustomErrorResult.IMPOSSIBLE_STATUS_TO_MODIFY);
    }
  }

}