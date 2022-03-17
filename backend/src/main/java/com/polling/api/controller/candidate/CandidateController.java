package com.polling.api.controller.candidate;

import com.polling.api.controller.candidate.dto.request.PatchCommentRequestDto;
import com.polling.api.controller.candidate.dto.request.SaveCandidateHistoryRequestDto;
import com.polling.api.controller.candidate.dto.request.SaveCommentRequestDto;
import com.polling.api.controller.candidate.dto.response.FindCandidateResponseDto;
import com.polling.api.controller.candidate.dto.response.FindProfileResponseDto;
import com.polling.api.controller.candidate.dto.response.FindVoteHistoryResponseDto;
import com.polling.api.controller.exception.CustomException;
import com.polling.api.controller.exception.ErrorCode;
import com.polling.api.service.candidate.CandidateService;
import com.polling.common.security.CurrentUser;
import com.polling.common.security.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping()
    @ApiOperation(value = "후보자들 목록 조회")
    public ResponseEntity<List<FindCandidateResponseDto>> getList() {
        List<FindCandidateResponseDto> responseDto = new ArrayList<>();
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "특정 후보자 정보 조회")
    public ResponseEntity<FindProfileResponseDto> getProfile(@PathVariable Long id) {
        FindProfileResponseDto responseDto = candidateService.getProfile(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "특정 후보자 득표 내역 조회")
    public ResponseEntity<List<FindVoteHistoryResponseDto>> getHistory(@PathVariable Long id) {
        List<FindVoteHistoryResponseDto> responseDto = candidateService.getHistory(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PostMapping("/history")
    @ApiOperation(value = "특정 후보자에게 투표")
    public ResponseEntity<Void> saveVoteHistory(@CurrentUser MemberDto memberDto, @RequestBody SaveCandidateHistoryRequestDto requestDto) {
        candidateService.saveVoteHistory(requestDto, memberDto.getNickname());
        return ResponseEntity.status(200).build();
    }
    @PostMapping("/comment")
    @ApiOperation(value = "특정 후보자에 응원 댓글 작성")
    public ResponseEntity<Void> saveComment(@CurrentUser MemberDto memberDto, @RequestBody SaveCommentRequestDto requestDto) {
        candidateService.saveComment(requestDto, memberDto.getNickname());
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/comment/{commentId}")
    @ApiOperation(value = "응원 댓글 수정")
    public ResponseEntity<Void> updateComment(@CurrentUser MemberDto memberDto, @PathVariable Long commentId, @RequestBody PatchCommentRequestDto requestDto) {
        candidateService.updateComment(commentId, requestDto, memberDto.getNickname());
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/comment/{commentId}")
    @ApiOperation(value = "응원 댓글 삭제")
    public ResponseEntity<Void> deleteComment(@CurrentUser MemberDto memberDto, @PathVariable Long commentId) {
        candidateService.deleteComment(commentId, memberDto.getNickname());
        return ResponseEntity.status(200).build();
    }
}
