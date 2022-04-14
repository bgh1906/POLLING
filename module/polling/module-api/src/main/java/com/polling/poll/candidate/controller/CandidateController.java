package com.polling.poll.candidate.controller;

import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.auth.CurrentUser;
import com.polling.auth.dto.MemberDto;
import com.polling.poll.candidate.dto.response.FindCandidateDetailsResponseDto;
import com.polling.poll.candidate.dto.response.FindCandidateHistoryByMemberResponseDto;
import com.polling.poll.candidate.dto.response.FindCandidateHistoryResponseDto;
import com.polling.poll.candidate.repository.CandidateHistoryQueryRepository;
import com.polling.poll.candidate.service.CandidateService;
import com.polling.poll.candidate.dto.request.SaveCandidateHistoryRequestDto;
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
    candidateService.saveVoteHistory(requestDto, memberDto.getId());
    return ResponseEntity.status(200).build();
  }

  @Trace
  @GetMapping("/limit/{candidateId}")
  @ApiOperation(value = "해당 후보자에게 투표가 가능한지 안한지 검사", notes = "투표 가능하면 200, 투표 불가능하면 400 에러 전송")
  public ResponseEntity<Void> didPollToday(@CurrentUser MemberDto memberDto,
      @PathVariable Long candidateId) {
    candidateService.canVoteToday(candidateId, memberDto.getId());
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

  @Retry
  @GetMapping("/members/{page}/{limit}")
  @ApiOperation(value = "해당 유저의 후보자 투표 내역 조회")
  public ResponseEntity<List<FindCandidateHistoryByMemberResponseDto>> getHistoryByUser(
      @CurrentUser MemberDto memberDto,
      @PathVariable int page, @PathVariable int limit) {
    List<FindCandidateHistoryByMemberResponseDto> responseDto = candidateHistoryQueryRepository
        .findByCandidateByMemberId(memberDto.getId(), page, limit);
    return ResponseEntity.status(200).body(responseDto);
  }

  @Retry
  @GetMapping("/polls/{pollsId}/{page}/{limit}")
  @ApiOperation(value = "해당 투표의 후보자 투표 내역 조회")
  public ResponseEntity<List<FindCandidateHistoryResponseDto>> getHistoryByUser(
      @CurrentUser MemberDto memberDto,
      @PathVariable(value = "pollsId") Long pollsId, @PathVariable int page,
      @PathVariable int limit) {
    List<FindCandidateHistoryResponseDto> responseDto = candidateHistoryQueryRepository
        .findByCandidateByPollId(pollsId, page, limit);
    return ResponseEntity.status(200).body(responseDto);
  }
}
