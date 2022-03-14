package com.polling.api.controller.vote;

import com.polling.api.controller.vote.dto.request.SaveVoteRequestDto;
import com.polling.api.controller.vote.dto.response.FindVoteResponseDto;
import com.polling.api.service.vote.VoteService;
import com.polling.common.security.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    @ApiOperation(value = "투표 생성")
    public ResponseEntity<Void> save(@RequestBody SaveVoteRequestDto requestDto) {
        String hostEmail = getCurrentUserEmail();
        voteService.saveVote(requestDto, hostEmail);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "득표 현황 랭킹 조회")
    public ResponseEntity<FindVoteResponseDto> getRanking(@PathVariable Long id) {
        FindVoteResponseDto responseDto = voteService.getRanking(id);
        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "투표 삭제")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "투표 종료")
    public ResponseEntity<Void> endVote(@PathVariable Long id) {
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/{id}&{status}")
    @ApiOperation(value = "투표 공개 <-> 비공개 스왑")
    public ResponseEntity<Void> changeStatus(@PathVariable Long id, @PathVariable Boolean status) {
        return ResponseEntity.status(200).build();
    }

    //todo: throw Custom exception 처리
    private String getCurrentUserEmail(){
        return SecurityUtils.getCurrentUsername().orElseThrow();
    }
}
