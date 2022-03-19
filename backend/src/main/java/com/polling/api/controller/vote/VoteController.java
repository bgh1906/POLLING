package com.polling.api.controller.vote;

import com.polling.api.controller.vote.dto.VoteResponseDto;
import com.polling.api.controller.vote.dto.request.SaveVoteRequestDto;
import com.polling.api.controller.vote.dto.response.FindVoteResponseDto;
import com.polling.api.queryrepository.VoteQueryRepository;
import com.polling.api.service.vote.VoteService;
import com.polling.common.security.CurrentUser;
import com.polling.common.security.dto.MemberDto;
import com.polling.core.entity.vote.status.VoteStatus;
import com.polling.core.repository.vote.VoteRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;
    private final VoteRepository voteRepository;
    private final VoteQueryRepository voteQueryRepository;

    @PostMapping
    @ApiOperation(value = "투표 생성")
    public ResponseEntity<Void> save(@CurrentUser MemberDto memberDto, @RequestBody SaveVoteRequestDto requestDto) {
        voteService.saveVote(requestDto, memberDto.getEmail());
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    @ApiOperation(value = "모든 투표 조회")
    public ResponseEntity<List<VoteResponseDto>> getVotes(){
        List<VoteResponseDto> responseDto = voteQueryRepository.findAll();
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/status/{status}")
    @ApiOperation(value = "Status별 투표 조회")
    public ResponseEntity<List<VoteResponseDto>> getVotesByStatus(@PathVariable VoteStatus status){
        List<VoteResponseDto> responseDto = voteQueryRepository.findVoteByStatus(status);
        return ResponseEntity.status(200).body(responseDto);
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
    @ApiOperation(value = "투표 status 변경")
    public ResponseEntity<Void> changeStatus(@PathVariable Long id, @PathVariable Boolean status) {
        return ResponseEntity.status(200).build();
    }

    //todo: throw Custom exception 처리
//    private String getCurrentUserEmail(){
//        return SecurityUtils.getCurrentUsername().orElseThrow();
//    }
}
