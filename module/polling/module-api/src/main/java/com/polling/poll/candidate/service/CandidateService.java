package com.polling.poll.candidate.service;


import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.dto.request.ModifyCandidateRequestDto;
import com.polling.poll.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.poll.candidate.dto.response.FindCandidateDetailsResponseDto;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import com.polling.poll.candidate.entity.CandidateHistory;
import com.polling.poll.candidate.repository.CandidateQueryRepository;
import com.polling.poll.candidate.repository.CandidateRepository;
import com.polling.poll.poll.entity.status.PollStatus;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CandidateService {

  private final CandidateRepository candidateRepository;
  private final CandidateQueryRepository candidateQueryRepository;
  private final MemberRepository memberRepository;

  @Transactional(readOnly = true)
  public FindCandidateDetailsResponseDto getProfile(Long candidateId) {
    Candidate candidate = getCandidate(candidateId);
    return FindCandidateDetailsResponseDto.of(candidate);
  }

  @Trace
  @Retry
  public void saveVoteHistory(SaveCandidateHistoryRequestDto requestDto, Long memberId) {
    if (requestDto.getVoteCount() <= 0) {
      throw new CustomException(CustomErrorResult.INVALID_VOTES);
    }
    Member member = getMember(memberId);
    Candidate candidate = getCandidate(requestDto.getCandidateId());

    validateVoteStatus(member, candidate);

    /* 후보자 투표 기록 생성 */
    CandidateHistory candidateHistory = CandidateHistory.createCandidateHistory(member, candidate,
        requestDto.getVoteCount(), requestDto.getTransactionId());

    /* 후보자 투표 기록 추가 */
    candidate.addHistory(candidateHistory);
  }

  @Trace
  public void canVoteToday(Long candidateId, Long memberId) {
    Member member = getMember(memberId);
    Candidate candidate = getCandidate(candidateId);
    validateVoteStatus(member, candidate);
  }

  @Trace
  public void modifyCandidate(Long candidateId, ModifyCandidateRequestDto requestDto) {
    Candidate candidate = getCandidate(candidateId);
    validateStatus(candidate.getPoll().getPollStatus());

    candidateQueryRepository.deleteGalleryById(candidateId);
    candidate.addGallery(new CandidateGallery(requestDto.getImagePath1()));
    candidate.addGallery(new CandidateGallery(requestDto.getImagePath2()));
    candidate.addGallery(new CandidateGallery(requestDto.getImagePath3()));
    candidate.changeName(requestDto.getName());
    candidate.changeProfile(requestDto.getProfile());
    candidate.changeThumbnail(requestDto.getThumbnail());
  }

  @Trace
  public void deleteCandidate(Long candidateId) {
    if (!candidateRepository.existsById(candidateId)) {
      throw new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND);
    }
    candidateQueryRepository.deleteGalleryById(candidateId);
    candidateRepository.deleteById(candidateId);
  }

  private Member getMember(Long memberId) {
    return memberRepository.findById(memberId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.USER_NOT_FOUND));
  }

  private Candidate getCandidate(Long candidateId) {
    return candidateRepository
        .findById(candidateId)
        .orElseThrow(() -> new CustomException(CustomErrorResult.CANDIDATE_NOT_FOUND));
  }

  private void validateStatus(PollStatus pollStatus) {
    if (pollStatus == PollStatus.IN_PROGRESS || pollStatus == PollStatus.DONE) {
      throw new CustomException(CustomErrorResult.IMPOSSIBLE_STATUS_TO_MODIFY);
    }
  }

  private void validateVoteStatus(Member member, Candidate candidate){
    if (candidateQueryRepository.existsByMemberIdAndPollIdInToday(
        member.getId(),
        candidate.getPoll().getId(),
        LocalDateTime.now().toLocalDate().atStartOfDay())) {
      throw new CustomException(CustomErrorResult.ALREADY_VOTES);
    }
  }
}
