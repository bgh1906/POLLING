package com.polling.candidate.controller;

import com.polling.auth.dto.MemberDto;
import com.polling.candidate.dto.request.ModifyCandidateRequestDto;
import com.polling.candidate.dto.request.ModifyCommentRequestDto;
import com.polling.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.candidate.dto.request.SaveCommentRequestDto;
import com.polling.candidate.dto.response.FindCandidateHistoryResponseDto;
import com.polling.candidate.dto.response.FindProfileResponseDto;
import com.polling.candidate.service.CandidateService;
import com.polling.queryrepository.CandidateHistoryQueryRepository;
import com.polling.security.CurrentUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;
    private final CandidateHistoryQueryRepository candidateHistoryQueryRepository;

    @PostMapping
    @ApiOperation(value = "특정 후보자에게 투표")
    public ResponseEntity<Void> saveVoteHistory(@CurrentUser MemberDto memberDto, @RequestBody SaveCandidateHistoryRequestDto requestDto) {
        candidateService.voteToCandidate(requestDto, memberDto.getId());
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{candidatesId}")
    @ApiOperation(value = "특정 후보자 정보 조회")
    public ResponseEntity<FindProfileResponseDto> getProfile(@PathVariable Long candidatesId) {
        FindProfileResponseDto responseDto = candidateService.getProfile(candidatesId);
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/history/{candidatesId}/{page}/{limit}")
    @ApiOperation(value = "특정 후보자 득표 내역 조회")
    public ResponseEntity<List<FindCandidateHistoryResponseDto>> getHistory(@PathVariable(value = "candidatesId") Long candidateId, @PathVariable int page, @PathVariable int limit) {
        List<FindCandidateHistoryResponseDto> responseDto = candidateHistoryQueryRepository
                .findByCandidateId(candidateId, page, limit);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PatchMapping("/{candidateId}")
    @ApiOperation(value = "특정 후보자 정보 수정", notes = "투표 상태가 unapproved or wait인 경우에만 가능")
    public ResponseEntity<FindProfileResponseDto> modifyProfile(@PathVariable Long candidateId, @RequestBody ModifyCandidateRequestDto requestDto) {
        candidateService.modifyCandidate(candidateId, requestDto);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{candidateId}")
    @ApiOperation(value = "특정 후보자 삭제", notes = "투표 상태가 unapproved or wait인 경우에만 가능")
    public ResponseEntity<FindProfileResponseDto> deleteProfile(@PathVariable Long candidateId) {
        candidateService.deleteCandidate(candidateId);
        return ResponseEntity.status(200).build();
    }


    @PostMapping("/comment")
    @ApiOperation(value = "특정 후보자에 응원 댓글 작성")
    public ResponseEntity<Void> saveComment(@CurrentUser MemberDto memberDto, @RequestBody SaveCommentRequestDto requestDto) {
        candidateService.saveComment(requestDto, memberDto.getId());
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/comment/{commentId}")
    @ApiOperation(value = "응원 댓글 수정")
    public ResponseEntity<Void> updateComment(@CurrentUser MemberDto memberDto, @PathVariable Long commentId, @RequestBody ModifyCommentRequestDto requestDto) {
        candidateService.updateComment(commentId, requestDto, memberDto.getId());
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/comment/{commentId}")
    @ApiOperation(value = "응원 댓글 삭제")
    public ResponseEntity<Void> deleteComment(@CurrentUser MemberDto memberDto, @PathVariable Long commentId) {
        candidateService.deleteComment(commentId, memberDto.getId());
        return ResponseEntity.status(200).build();
    }
}
