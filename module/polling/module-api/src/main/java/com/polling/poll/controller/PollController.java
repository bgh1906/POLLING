package com.polling.poll.controller;

import com.polling.auth.dto.MemberDto;
import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.request.ModifyPollRequestDto;
import com.polling.poll.dto.response.FindPollPageResponseDto;
import com.polling.poll.dto.request.SavePollRequestDto;
import com.polling.poll.dto.response.FindPollResponseDto;
import com.polling.poll.service.PollService;
import com.polling.queryrepository.PollQueryRepository;
import com.polling.security.CurrentUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;
    private final PollQueryRepository pollQueryRepository;

    @PostMapping
    @ApiOperation(value = "투표 생성")
    public ResponseEntity<Void> save(@CurrentUser MemberDto memberDto, @RequestBody SavePollRequestDto requestDto) {
        pollService.savePoll(requestDto, memberDto.getEmail());
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{status}/{page}/{limit}")
    @ApiOperation(value = "상태에 따른 투표 페이지 조회",  notes = "status는 unapproved, wait, progress, done으로 구분," +
            "page는 0부터 시작하며, limit은 가져올 데이터의 개수")
    public ResponseEntity<List<FindPollPageResponseDto>> getProgressPollPage(@PathVariable String status, @PathVariable int page, @PathVariable int limit){
        PollStatus pollStatus = PollStatus.findStatusByName(status);
        List<FindPollPageResponseDto> responseDto = pollQueryRepository.findPollPageByStatus(pollStatus, page, limit);
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/ranking/{pollId}")
    @ApiOperation(value = "해당 투표의 정보 및 후보자 정보를 득표 순위를 기준으로 정렬해서 조회")
    public ResponseEntity<FindPollResponseDto> getRanking(@PathVariable Long pollId) {
        FindPollResponseDto responseDto = pollService.getRanking(pollId);
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/{pollId}")
    @ApiOperation(value = "해당 투표의 정보 및 후보자 정보를 조회")
    public ResponseEntity<FindPollResponseDto> getPollInfo(@PathVariable Long pollId) {
        FindPollResponseDto responseDto = pollService.getPollInfo(pollId);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PatchMapping("/{pollId}")
    @ApiOperation(value = "투표 수정", notes = "상태가 unapproved인 경우에만 가능")
    public ResponseEntity<Void> modifyPollInfo(@PathVariable Long pollId, @RequestBody ModifyPollRequestDto requestDto) {
        pollService.modifyPoll(pollId, requestDto);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{pollId}")
    @ApiOperation(value = "투표 삭제")
    public ResponseEntity<Void> delete(@PathVariable Long pollId) {
        pollService.delete(pollId);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/{pollId}/{status}")
    @ApiOperation(value = "투표 status 변경")
    public ResponseEntity<Void> changeStatus(@PathVariable Long pollId, @PathVariable String status) {
        pollService.updateStatus(pollId, status);
        return ResponseEntity.status(200).build();
    }

}
