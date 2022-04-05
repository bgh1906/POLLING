package com.polling.token.service;

import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.Member;
import com.polling.member.repository.MemberRepository;
import com.polling.poll.candidate.entity.Candidate;
import com.polling.poll.candidate.entity.CandidateGallery;
import com.polling.poll.candidate.repository.CandidateGalleryRepository;
import com.polling.poll.candidate.repository.CandidateRepository;
import com.polling.token.dto.request.SaveTokenUsageHistoryRequestDto;
import com.polling.token.entity.TokenUsageHistory;
import com.polling.token.repository.TokenUsageHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

  private final TokenUsageHistoryRepository tokenUsageHistoryRepository;
  private final MemberRepository memberRepository;
  private final CandidateRepository candidateRepository;
  private final CandidateGalleryRepository candidateGalleryRepository;

  public void saveMemberTokenUsageToCandidate(SaveTokenUsageHistoryRequestDto requestDto,
      Long memberId) {
    Member member = getMember(memberId);
    Candidate candidate = getCandidate(requestDto.getCandidateId());
//    CandidateGallery gallery = candidateGalleryRepository.findById(
//        requestDto.getCandidateGalleryId()).orElseThrow(IllegalStateException::new);

    // simple API
    CandidateGallery gallery = candidate.getGalleries().get(2);

    TokenUsageHistory tokenUsageHistory = TokenUsageHistory.builder()
        .member(member)
        .candidate(candidate)
        .gallery(gallery)
        .build();

    tokenUsageHistoryRepository.save(tokenUsageHistory);
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
}
