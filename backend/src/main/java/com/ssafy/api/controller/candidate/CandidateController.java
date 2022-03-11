package com.ssafy.api.controller.candidate;

import com.ssafy.api.controller.candidate.dto.request.SaveCandidateRequestDto;
import com.ssafy.api.controller.candidate.dto.response.FindCommentReponseDto;
import com.ssafy.api.controller.candidate.dto.response.FindProfileResponseDto;
import com.ssafy.api.controller.candidate.dto.response.FindVoteHistoryResponseDto;
import com.ssafy.api.controller.user.dto.request.SaveUserRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
@RequiredArgsConstructor
public class CandidateController {

    @PostMapping
    @ApiOperation(value = "후보 등록")
    public ResponseEntity<Void> save(@RequestBody SaveCandidateRequestDto requestDto) {
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "후보자 정보 조회")
    public ResponseEntity<FindProfileResponseDto> getProfile(@PathVariable Long id) {
        FindProfileResponseDto responseDto = new FindProfileResponseDto();
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "특정 후보자 득표 내역 조회")
    public ResponseEntity<FindVoteHistoryResponseDto> getHistory(@PathVariable Long id) {
        FindVoteHistoryResponseDto responseDto = new FindVoteHistoryResponseDto();
        return ResponseEntity.status(200).body(responseDto);
    }

    @PostMapping("/comment")
    @ApiOperation(value = "응원 댓글 작성")
    public ResponseEntity<Void> saveComment(@RequestBody SaveUserRequestDto requestDto) {
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/comment/{commentId}")
    @ApiOperation(value = "응원 댓글 수정")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId) {
        FindCommentReponseDto reponseDto = new FindCommentReponseDto();
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/comment/{commentId}")
    @ApiOperation(value = "응원 댓글 삭제")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.status(200).build();
    }


}
