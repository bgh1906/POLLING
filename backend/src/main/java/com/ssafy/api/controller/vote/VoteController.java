package com.ssafy.api.controller.vote;

import com.ssafy.api.controller.vote.dto.request.FindVoteRankingRsponseDto;
import com.ssafy.api.controller.user.dto.request.SaveUserRequestDto;
import com.ssafy.api.controller.vote.dto.request.SaveVoteRequestDto;
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

    @PostMapping
    @ApiOperation(value = "투표 생성")
    public ResponseEntity<Void> save(@RequestBody SaveVoteRequestDto requestDto) {
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "대회 정보 조회")
    public ResponseEntity<Void> getHistory (@PathVariable Long id) {
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/rank/{id}")
    @ApiOperation(value = "득표 현황 랭킹 조회")
    public ResponseEntity<List<FindVoteRankingRsponseDto>> getRanking(@PathVariable Long id) {
        List<FindVoteRankingRsponseDto> responseDto = new ArrayList<>();
        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "투표 삭제")
    public ResponseEntity<Void> delete() {
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "투표 종료")
    public ResponseEntity<Void> endVote(@PathVariable Long id) {
        return ResponseEntity.status(200).build();
    }

}
