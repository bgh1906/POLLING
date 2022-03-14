package com.ssafy.api.controller.vote;

import com.ssafy.api.controller.vote.dto.request.SaveVoteRequestDto;
import com.ssafy.api.controller.vote.dto.response.FindVoteResponseDto;
import com.ssafy.api.service.user.UserService;
import com.ssafy.api.service.vote.VoteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vote")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    @ApiOperation(value = "투표 생성")
    public ResponseEntity<Void> save(@RequestBody SaveVoteRequestDto requestDto) {
        voteService.saveVote(requestDto);
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

}
