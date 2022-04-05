package com.polling.token.controller;

import com.polling.auth.CurrentUser;
import com.polling.auth.dto.MemberDto;
import com.polling.token.dto.request.SaveTokenUsageHistoryRequestDto;
import com.polling.token.dto.response.FindTokenUsageHistoryResponseDto;
import com.polling.token.repository.TokenUsageHistoryQueryRepository;
import com.polling.token.service.TokenService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/use-tokens")
@RequiredArgsConstructor
public class TokenController {

  private final TokenService tokenService;
  private final TokenUsageHistoryQueryRepository historyQueryRepository;

  @PostMapping("/candidates")
  @ApiOperation(value = "시크릿 이미지로 토큰 사용 내역 저장")
  public ResponseEntity<Void> saveTokenUsageHistory(
      @CurrentUser MemberDto memberDto, @RequestBody SaveTokenUsageHistoryRequestDto requestDto) {
    tokenService.saveMemberTokenUsageToCandidate(requestDto, memberDto.getId());
    return ResponseEntity.status(200).build();
  }

  @GetMapping("/candidates/{candidateId}")
  @ApiOperation(value = "해당 후보자에 회원이 사용한 토큰 내역 조회")
  public ResponseEntity<List<FindTokenUsageHistoryResponseDto>> getTokenUsageHistoryFromCandidate(
      @CurrentUser MemberDto memberDto, @PathVariable Long candidateId) {
    List<FindTokenUsageHistoryResponseDto> responseDtos = historyQueryRepository.findSecretByMemberIdAndCandidateId(
        memberDto.getId(), candidateId);
    return ResponseEntity.status(200).body(responseDtos);
  }
}
