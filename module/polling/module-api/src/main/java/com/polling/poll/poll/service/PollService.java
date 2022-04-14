package com.polling.poll.poll.service;


import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.entity.status.MemberRole;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.dto.request.AddCandidateRequestDto;
import com.polling.poll.candidate.dto.response.FindAdminCandidateResponseDto;
import com.polling.poll.candidate.dto.response.FindAnonymousCandidateResponseDto;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.poll.dto.request.ApprovePollRequestDto;
import com.polling.poll.poll.dto.request.ModifyPollRequestDto;
import com.polling.poll.poll.dto.request.SavePollRequestDto;
import com.polling.poll.poll.dto.response.FindPollWithCandidateResponseDto;
import com.polling.poll.poll.dto.response.FindSimplePollResponseDto;
import com.polling.poll.poll.entity.Poll;
import com.polling.poll.poll.entity.status.PollStatus;
import com.polling.poll.poll.repository.PollQueryRepository;
import com.polling.poll.poll.repository.PollRepository;
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
    List<FindAnonymousCandidateResponseDto> responseDtos = poll.getCandidates().stream()
        .map(candidate -> new FindAnonymousCandidateResponseDto(candidate.getId(),
            candidate.getContractIndex(),
            candidate.getName(),
            candidate.getThumbnail())).collect(Collectors.toList());

    return FindSimplePollResponseDto.of(responseDtos, poll);
  }

  @Trace
  @Retry
  @Transactional(readOnly = true)
  public FindPollWithCandidateResponseDto findPollAllInfo(Long pollId) {
    Poll poll = getPoll(pollId);
    List<Candidate> candidates = poll.getCandidates();

    List<FindAdminCandidateResponseDto> list = candidates.stream()
        .map(candidate -> FindAdminCandidateResponseDto.builder()
            .candidateId(candidate.getId())
            .candidateIndex(candidate.getContractIndex())
            .name(candidate.getName())
            .thumbnail(candidate.getThumbnail())
            .galleries(candidate.getGalleries())
            .profile(candidate.getProfile())
            .build()).collect(Collectors.toList());
    return FindPollWithCandidateResponseDto.of(list, poll);
  }

  @Trace
  public void deletePoll(Long pollId) {
    getPoll(pollId);
    pollQueryRepository.deleteImageByPollId(pollId);
    pollQueryRepository.deleteCandidateByPollId(pollId);
    pollRepository.deleteById(pollId);
  }

  @Trace
  public void addCandidate(AddCandidateRequestDto requestDto) {
    Poll poll = getPoll(requestDto.getPollId());
    validateStatus(poll);
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
  public void approvePoll(ApprovePollRequestDto requestDto) {
    Poll poll = getPoll(requestDto.getPollId());
    validateStatus(poll);
    int size = poll.getCandidates().size();

    for (int i = 0; i < size; i++) {
      poll.getCandidates().get(i).changeContractIndex(requestDto.getListCandidateIndex().get(i));
    }

    poll.changePollStatus(PollStatus.WAIT);
  }

  /**
   * 1분 단위로 IN_PROGRESS 상태의 투표를 점검하고 종료 시간이 됐으면 DONE으로 변경
   */
  @Scheduled(fixedRate = 60000)
  public void checkPollEndTime() {
    List<Poll> polls = pollQueryRepository.findByCurrentBeforeEndTime(LocalDateTime.now());
    if (!polls.isEmpty()) {
      polls.forEach(poll -> poll.changePollStatus(PollStatus.DONE));
    }
  }

  /**
   * 1분 단위로 WAIT 상태의 투표를 점검하고 시작 시간이 됐으면 IN_PROGRESS로 변경
   */
  @Scheduled(fixedRate = 60000)
  public void checkPollStartTime() {
    List<Poll> polls = pollQueryRepository.findByCurrentBeforeStartTime(LocalDateTime.now());
    if (!polls.isEmpty()) {
      polls.forEach(poll -> poll.changePollStatus(PollStatus.IN_PROGRESS));
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