package com.polling.poll.poll.service;


import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.entity.candidate.Candidate;
import com.polling.entity.candidate.CandidateGallery;
import com.polling.entity.candidate.CandidateHistory;
import com.polling.entity.member.Member;
import com.polling.entity.poll.status.PollStatus;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.poll.dto.candidate.request.ModifyCandidateRequestDto;
import com.polling.poll.dto.candidate.response.FindCandidateDetailsResponseDto;
import com.polling.poll.dto.comment.response.FindCommentResponseDto;
import com.polling.poll.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import com.polling.queryrepository.CandidateQueryRepository;
import com.polling.queryrepository.CommentQueryRepository;
import com.polling.repository.candidate.CandidateHistoryRepository;
import com.polling.repository.candidate.CandidateRepository;
import com.polling.repository.member.MemberRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CandidateService {

  private final CandidateRepository candidateRepository;
  private final CommentQueryRepository commentQueryRepository;
  private final CandidateHistoryQueryRepository candidateHistoryQueryRepository;
  private final CandidateHistoryRepository candidateHistoryRepository;
  private final CandidateQueryRepository candidateQueryRepository;
  private final MemberRepository memberRepository;

  @Transactional(readOnly = true)
  public FindCandidateDetailsResponseDto getProfile(Long candidateId) {
    Candidate candidate = getCandidate(candidateId);
    List<FindCommentResponseDto> comments = commentQueryRepository
        .findAllByCandidateId(candidateId);
    return FindCandidateDetailsResponseDto.of(candidate, comments);
  }

  @Trace
  @Retry
  public void saveVoteHistory(SaveCandidateHistoryRequestDto requestDto, Long id) {
    if (requestDto.getVoteCount() <= 0) {
      throw new CustomException(CustomErrorResult.INVALID_VOTES);
    }
    Member member = getMember(id);
    Candidate candidate = getCandidate(requestDto.getCandidateId());

    if (candidateHistoryQueryRepository.existsByMemberIdAndPollIdInToday(
        member.getId(),
        candidate.getPoll().getId(),
        LocalDateTime.now())) {
      throw new CustomException(CustomErrorResult.ALREADY_VOTES);
    }

    CandidateHistory history = CandidateHistory.builder()
        .member(member)
        .candidate(candidate)
        .voteCount(requestDto.getVoteCount())
        .transactionId(requestDto.getTransactionId())
        .build();

    candidateHistoryRepository.save(history);
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
}
