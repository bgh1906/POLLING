package com.polling.poll.controller;

import com.polling.auth.dto.MemberDto;
import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.PollResponseDto;
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

    @GetMapping
    @ApiOperation(value = "모든 투표 조회")
    public ResponseEntity<List<PollResponseDto>> getVotes(){
        List<PollResponseDto> responseDto = pollQueryRepository.findAll();
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/status/{status}")
    @ApiOperation(value = "Status별 투표 조회")
    public ResponseEntity<List<PollResponseDto>> getVotesByStatus(@PathVariable PollStatus status){
        List<PollResponseDto> responseDto = pollQueryRepository.findVoteByStatus(status);
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

    //todo: throw Custom exception 처리
//    private String getCurrentUserEmail(){
//        return SecurityUtils.getCurrentUsername().orElseThrow();
//    }
}
