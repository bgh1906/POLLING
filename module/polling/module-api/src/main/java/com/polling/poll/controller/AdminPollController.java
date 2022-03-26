package com.polling.poll.controller;

import com.polling.aop.annotation.Retry;
import com.polling.aop.annotation.Trace;
import com.polling.auth.dto.MemberDto;
import com.polling.poll.dto.candidate.request.ModifyCandidateRequestDto;
import com.polling.poll.dto.candidate.response.FindCandidateDetailsResponseDto;
import com.polling.poll.dto.request.ModifyPollRequestDto;
import com.polling.poll.dto.request.SavePollRequestDto;
import com.polling.poll.dto.response.FindPollAndCandidateResponseDto;
import com.polling.poll.service.PollService;
import com.polling.queryrepository.PollQueryRepository;
import com.polling.security.CurrentUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polls/admin")
@RequiredArgsConstructor
public class AdminPollController {

    private final PollService pollService;

    @Retry
    @Trace
    @PostMapping
    @ApiOperation(value = "투표 생성")
    public ResponseEntity<Void> save(@CurrentUser MemberDto memberDto, @RequestBody SavePollRequestDto requestDto) {
        pollService.savePoll(requestDto, memberDto.getId());
        return ResponseEntity.status(200).build();
    }

    @Retry
    @Trace
    @GetMapping("/{pollId}")
    @ApiOperation(value = "해당 투표의 정보 및 후보자 정보를 조회")
    public ResponseEntity<FindPollAndCandidateResponseDto> getPollInfo(@PathVariable Long pollId) {
        FindPollAndCandidateResponseDto responseDto = pollService.findPollAllInfo(pollId);
        return ResponseEntity.status(200).body(responseDto);
    }

    @Retry
    @Trace
    @PutMapping("/{pollId}")
    @ApiOperation(value = "투표 수정", notes = "상태가 unapproved, wait인 경우에만 가능")
    public ResponseEntity<Void> modifyPollInfo(@PathVariable Long pollId, @RequestBody ModifyPollRequestDto requestDto) {
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

    @PatchMapping("/{pollId}/{status}")
    @ApiOperation(value = "투표 status 변경")
    public ResponseEntity<Void> changeStatus(@PathVariable Long pollId, @PathVariable String status) {
        pollService.modifyStatus(pollId, status);
        return ResponseEntity.status(200).build();
    }

}
