package com.polling.poll.comment.controller;

import com.polling.entity.poll.status.PollStatus;
import com.polling.poll.dto.response.FindPollPageResponseDto;
import com.polling.poll.dto.response.FindSimplePollResponseDto;
import com.polling.poll.service.PollService;
import com.polling.queryrepository.PollQueryRepository;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polls")
@RequiredArgsConstructor
public class PollController {

  private final PollService pollService;
  private final PollQueryRepository pollQueryRepository;

  @GetMapping("/{status}/{page}/{limit}")
  @ApiOperation(value = "상태 별로 투표 페이지 조회", notes =
      "status는 unapproved, wait, progress, done으로 구분, page는 0부터 시작하며, limit은 가져올 데이터의 개수")
  public ResponseEntity<List<FindPollPageResponseDto>> getProgressPollPage(
      @PathVariable String status, @PathVariable int page, @PathVariable int limit) {
    PollStatus pollStatus = PollStatus.findStatusByName(status);
    List<FindPollPageResponseDto> responseDto = pollQueryRepository.findPollPageByStatus(
        pollStatus, page, limit);
    return ResponseEntity.status(200).body(responseDto);
  }

  @GetMapping("/{pollId}")
  @ApiOperation(value = "해당 투표의 정보 및 후보자 정보를 조회")
  public ResponseEntity<FindSimplePollResponseDto> getPoll(@PathVariable Long pollId) {
    FindSimplePollResponseDto responseDto = pollService.findPollThumbnail(pollId);
    return ResponseEntity.status(200).body(responseDto);
  }

}
