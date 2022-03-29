package com.polling.poll.controller;

import com.polling.aop.annotation.Trace;
import com.polling.auth.dto.MemberDto;
import com.polling.poll.dto.candidate.request.SaveCandidateRequestDto;
import com.polling.poll.dto.request.ModifyPollRequestDto;
import com.polling.poll.dto.request.SavePollRequestDto;
import com.polling.poll.dto.response.FindPollWithCandidateResponseDto;
import com.polling.poll.service.PollService;
import com.polling.security.CurrentUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polls/admin")
@RequiredArgsConstructor
public class AdminPollController {

  private final PollService pollService;

  @Trace
  @PostMapping
  @ApiOperation(value = "투표 생성")
  public ResponseEntity<Void> save(@CurrentUser MemberDto memberDto,
      @RequestBody SavePollRequestDto requestDto) {
    pollService.savePoll(requestDto, memberDto.getId());
    return ResponseEntity.status(200).build();
  }

  @Trace
  @GetMapping("/{pollId}")
  @ApiOperation(value = "해당 투표의 정보 및 후보자 정보를 조회")
  public ResponseEntity<FindPollWithCandidateResponseDto> getPollInfo(@PathVariable Long pollId) {
    FindPollWithCandidateResponseDto responseDto = pollService.findPollAllInfo(pollId);
    return ResponseEntity.status(200).body(responseDto);
  }

  @Trace
  @PostMapping("/candidate")
  @ApiOperation(value = "후보자 추가", notes = "상태가 unapproved, wait인 경우에만 가능")
  public ResponseEntity<Void> addCandidate(@RequestBody SaveCandidateRequestDto requestDto) {
    pollService.addCandidate(requestDto);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @PutMapping("/{pollId}")
  @ApiOperation(value = "투표 수정", notes = "상태가 unapproved, wait인 경우에만 가능")
  public ResponseEntity<Void> modifyPollInfo(@PathVariable Long pollId,
      @RequestBody ModifyPollRequestDto requestDto) {
    pollService.modifyPoll(pollId, requestDto);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @DeleteMapping("/{pollId}")
  @ApiOperation(value = "투표 삭제")
  public ResponseEntity<Void> delete(@PathVariable Long pollId) {
    pollService.deletePoll(pollId);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @PatchMapping("/{pollId}/{status}")
  @ApiOperation(value = "투표 status 변경")
  public ResponseEntity<Void> changeStatus(@PathVariable Long pollId,
      @PathVariable String status) {
    pollService.modifyStatus(pollId, status);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @PatchMapping("/open/{pollId}")
  @ApiOperation(value = "투표 공개 옵션 변경", notes = "true면 false로 false면 true로 변경")
  public ResponseEntity<Void> changeOpenStatus(@PathVariable Long pollId) {
    pollService.changeOpenStatus(pollId);
    return ResponseEntity.status(200).build();
  }

}
