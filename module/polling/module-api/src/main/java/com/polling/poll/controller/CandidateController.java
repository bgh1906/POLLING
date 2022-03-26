package com.polling.poll.controller;

import com.polling.auth.dto.MemberDto;
import com.polling.poll.dto.candidate.request.AddVoteCountRequestDto;
import com.polling.poll.dto.candidate.response.FindCandidateHistoryResponseDto;
import com.polling.poll.dto.candidate.response.FindCandidateDetailsResponseDto;
import com.polling.poll.service.CandidateService;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import com.polling.security.CurrentUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;
    private final CandidateHistoryQueryRepository candidateHistoryQueryRepository;

    @PostMapping
    @ApiOperation(value = "특정 후보자에게 투표")
    public ResponseEntity<Void> addVoteCount(@CurrentUser MemberDto memberDto, @RequestBody AddVoteCountRequestDto requestDto) {
        candidateService.addVoteCount(requestDto, memberDto.getId());
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{candidatesId}")
    @ApiOperation(value = "특정 후보자 정보 조회")
    public ResponseEntity<FindCandidateDetailsResponseDto> getProfile(@PathVariable Long candidatesId) {
        FindCandidateDetailsResponseDto responseDto = candidateService.getProfile(candidatesId);
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("{candidatesId}/{page}/{limit}")
    @ApiOperation(value = "특정 후보자 득표 내역 조회")
    public ResponseEntity<List<FindCandidateHistoryResponseDto>> getHistory(@PathVariable(value = "candidatesId") Long candidateId, @PathVariable int page, @PathVariable int limit) {
        List<FindCandidateHistoryResponseDto> responseDto = candidateHistoryQueryRepository
                .findByCandidateId(candidateId, page, limit);
        return ResponseEntity.status(200).body(responseDto);
    }
}
