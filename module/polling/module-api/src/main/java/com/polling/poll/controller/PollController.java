package com.polling.poll.controller;

import com.polling.auth.dto.MemberDto;
import com.polling.entity.poll.status.PollStatus;
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

import java.util.ArrayList;
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
    @ApiOperation(value = "현재 진행중인 투표 페이지 조회",  notes = "status는 unapproved, wait, progress, done으로 구분," +
            "page는 0부터 시작하며, limit은 가져올 데이터의 개수")
    public ResponseEntity<List<FindPollPageResponseDto>> getProgressPollPage(@PathVariable String status, @PathVariable int page, @PathVariable int limit){
        PollStatus pollStatus = PollStatus.findStatusByName(status);
        List<FindPollPageResponseDto> responseDto = pollQueryRepository.findPollPageByStatus(pollStatus, page, limit);
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "득표 현황 랭킹 조회")
    public ResponseEntity<FindPollResponseDto> getRanking(@PathVariable Long id) {
        FindPollResponseDto responseDto = pollService.getRanking(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "투표 삭제")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pollService.delete(id);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/{id}&{status}")
    @ApiOperation(value = "투표 status 변경")
    public ResponseEntity<Void> changeStatus(@PathVariable Long id, @PathVariable PollStatus status) {
        pollService.updateStatus(id, status);
        return ResponseEntity.status(200).build();
    }

}
