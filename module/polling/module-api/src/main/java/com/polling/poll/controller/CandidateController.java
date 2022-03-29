package com.polling.poll.controller;

import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.auth.dto.MemberDto;
import com.polling.poll.dto.candidate.response.FindCandidateDetailsResponseDto;
import com.polling.poll.dto.candidate.response.FindCandidateHistoryResponseDto;
import com.polling.poll.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.poll.service.CandidateService;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import com.polling.security.CurrentUser;
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
@RequestMapping("/api/polls/candidates")
@RequiredArgsConstructor
public class CandidateController {

  private final CandidateService candidateService;
  private final CandidateHistoryQueryRepository candidateHistoryQueryRepository;

  @Trace
  @PostMapping
  @ApiOperation(value = "특정 후보자의 득표 내역 저장")
  public ResponseEntity<Void> addVoteHistory(@CurrentUser MemberDto memberDto,
      @RequestBody SaveCandidateHistoryRequestDto requestDto) {
    candidateService.addCandidateHistory(requestDto, memberDto.getId());
    return ResponseEntity.status(200).build();
  }

  @GetMapping("/{candidatesId}")
  @ApiOperation(value = "특정 후보자 정보 조회")
  public ResponseEntity<FindCandidateDetailsResponseDto> getProfile(
      @PathVariable Long candidatesId) {
    FindCandidateDetailsResponseDto responseDto = candidateService.getProfile(candidatesId);
    return ResponseEntity.status(200).body(responseDto);
  }

  @Retry
  @GetMapping("{candidatesId}/{page}/{limit}")
  @ApiOperation(value = "특정 후보자 득표 내역 조회")
  public ResponseEntity<List<FindCandidateHistoryResponseDto>> getHistory(
      @PathVariable(value = "candidatesId") Long candidateId, @PathVariable int page,
      @PathVariable int limit) {
    List<FindCandidateHistoryResponseDto> responseDto = candidateHistoryQueryRepository
        .findByCandidateId(candidateId, page, limit);
    return ResponseEntity.status(200).body(responseDto);
  }
}
