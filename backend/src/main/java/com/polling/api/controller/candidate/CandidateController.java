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
    public ResponseEntity<Void> saveVoteHistory(@RequestBody SaveCandidateHistoryRequestDto requestDto) {
        String userName = SecurityUtils.getCurrentUsername()
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        candidateService.saveVoteHistory(requestDto, userName);
        return ResponseEntity.status(200).build();
    }
    @PostMapping("/comment")
    @ApiOperation(value = "특정 후보자에 응원 댓글 작성")
    public ResponseEntity<Void> saveComment(@RequestBody SaveCommentRequestDto requestDto) {
        String userName = SecurityUtils.getCurrentUsername()
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        candidateService.saveComment(requestDto, userName);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/comment/{commentId}")
    @ApiOperation(value = "응원 댓글 수정")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId, @RequestBody PatchCommentRequestDto requestDto) {
        String userName = SecurityUtils.getCurrentUsername()
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        candidateService.updateComment(commentId, requestDto, userName);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/comment/{commentId}")
    @ApiOperation(value = "응원 댓글 삭제")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        String userName = SecurityUtils.getCurrentUsername()
                .orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        candidateService.deleteComment(commentId, userName);
        return ResponseEntity.status(200).build();
    }
}
