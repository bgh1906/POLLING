package com.polling.poll.poll.controller;

import com.polling.aop.annotation.Trace;
import com.polling.auth.CurrentUser;
import com.polling.auth.dto.MemberDto;
import com.polling.exception.CustomErrorResult;
import com.polling.exception.CustomException;
import com.polling.member.entity.status.MemberRole;
import com.polling.poll.candidate.dto.request.AddCandidateRequestDto;
import com.polling.poll.poll.dto.request.ApprovePollRequestDto;
import com.polling.poll.poll.dto.request.ModifyPollRequestDto;
import com.polling.poll.poll.dto.request.SavePollRequestDto;
import com.polling.poll.poll.dto.response.FindPollWithCandidateResponseDto;
import com.polling.poll.poll.service.PollService;
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
    validateMemberRole(memberDto);
    pollService.savePoll(requestDto, memberDto.getId());
    return ResponseEntity.status(200).build();
  }

  @Trace
  @GetMapping("/{pollId}")
  @ApiOperation(value = "해당 투표의 정보 및 후보자 정보를 조회")
  public ResponseEntity<FindPollWithCandidateResponseDto> getPollInfo(
      @CurrentUser MemberDto memberDto, @PathVariable Long pollId) {
    validateMemberRole(memberDto);
    FindPollWithCandidateResponseDto responseDto = pollService.findPollAllInfo(pollId);
    return ResponseEntity.status(200).body(responseDto);
  }

  @Trace
  @PostMapping("/candidate")
  @ApiOperation(value = "후보자 추가", notes = "상태가 unapproved, wait인 경우에만 가능")
  public ResponseEntity<Void> addCandidate(@CurrentUser MemberDto memberDto,
      @RequestBody AddCandidateRequestDto requestDto) {
    validateMemberRole(memberDto);
    pollService.addCandidate(requestDto);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @PutMapping("/{pollId}")
  @ApiOperation(value = "투표 수정", notes = "상태가 unapproved, wait인 경우에만 가능")
  public ResponseEntity<Void> modifyPollInfo(@CurrentUser MemberDto memberDto,
      @PathVariable Long pollId,
      @RequestBody ModifyPollRequestDto requestDto) {
    validateMemberRole(memberDto);
    pollService.modifyPoll(pollId, requestDto);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @DeleteMapping("/{pollId}")
  @ApiOperation(value = "투표 삭제")
  public ResponseEntity<Void> delete(@CurrentUser MemberDto memberDto, @PathVariable Long pollId) {
    validateMemberRole(memberDto);
    pollService.deletePoll(pollId);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @PatchMapping("/wait")
  @ApiOperation(value = "투표 승인")
  public ResponseEntity<Void> approvePoll(@CurrentUser MemberDto memberDto,
      @RequestBody ApprovePollRequestDto requestDto) {
    validateMemberRole(memberDto);
    pollService.approvePoll(requestDto);
    return ResponseEntity.status(200).build();
  }

  @Trace
  @PatchMapping("/open/{pollId}")
  @ApiOperation(value = "투표 공개 옵션 변경", notes = "true면 false로 false면 true로 변경")
  public ResponseEntity<Void> changeOpenStatus(@CurrentUser MemberDto memberDto,
      @PathVariable Long pollId) {
    validateMemberRole(memberDto);
    pollService.changeOpenStatus(pollId);
    return ResponseEntity.status(200).build();
  }

  private void validateMemberRole(MemberDto memberDto) {
    if (!(memberDto.getMemberRole().contains(MemberRole.ROLE_ADMIN) || memberDto.getMemberRole()
        .contains(MemberRole.ROLE_COMPANY))) {
      throw new CustomException(CustomErrorResult.UNAUTHORIZED_MEMBER_ROLE);
    }
  }

}
