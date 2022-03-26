package com.polling.poll.controller;

import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.poll.dto.candidate.request.ModifyCandidateRequestDto;
import com.polling.poll.dto.candidate.response.FindCandidateDetailsResponseDto;
import com.polling.poll.service.CandidateService;
import com.polling.repository.candidate.CandidateRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polls/admin/candidates")
@RequiredArgsConstructor
public class AdminCandidateController {

    private final CandidateService candidateService;
    private final CandidateRepository candidateRepository;

    @PatchMapping("/{candidateId}")
    @ApiOperation(value = "특정 후보자 정보 수정", notes = "투표 상태가 unapproved or wait인 경우에만 가능")
    public ResponseEntity<FindCandidateDetailsResponseDto> modifyCandidate(@PathVariable Long candidateId, @RequestBody ModifyCandidateRequestDto requestDto) {
        candidateService.modifyCandidate(candidateId, requestDto);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{candidateId}")
    @ApiOperation(value = "특정 후보자 삭제", notes = "투표 상태가 unapproved or wait인 경우에만 가능")
    public ResponseEntity<FindCandidateDetailsResponseDto> deleteCandidate(@PathVariable Long candidateId) {
        candidateService.deleteCandidate(candidateId);
        return ResponseEntity.status(200).build();
    }

}
