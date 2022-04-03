package com.polling.poll.comment.controller;

import com.polling.aop.annotation.Trace;
import com.polling.poll.dto.candidate.request.ModifyCandidateRequestDto;
import com.polling.poll.dto.candidate.response.FindCandidateDetailsResponseDto;
import com.polling.poll.service.CandidateService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polls/admin/candidates")
@RequiredArgsConstructor
public class AdminCandidateController {

  private final CandidateService candidateService;

  @Trace
  @PutMapping("/{candidateId}")
  @ApiOperation(value = "특정 후보자 정보 수정", notes = "투표 상태가 unapproved or wait인 경우에만 가능")
  public ResponseEntity<FindCandidateDetailsResponseDto> modifyCandidate(
      @PathVariable Long candidateId, @RequestBody ModifyCandidateRequestDto requestDto) {
    candidateService.modifyCandidate(candidateId, requestDto);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @DeleteMapping("/{candidateId}")
  @ApiOperation(value = "특정 후보자 삭제", notes = "투표 상태가 unapproved or wait인 경우에만 가능")
  public ResponseEntity<FindCandidateDetailsResponseDto> deleteCandidate(
      @PathVariable Long candidateId) {
    candidateService.deleteCandidate(candidateId);
    return ResponseEntity.status(200).build();
  }
}
