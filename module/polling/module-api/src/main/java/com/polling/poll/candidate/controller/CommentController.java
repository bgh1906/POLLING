package com.polling.poll.candidate.controller;

import com.polling.aop.annotation.Trace;
import com.polling.auth.dto.MemberDto;
import com.polling.poll.dto.comment.request.ModifyCommentRequestDto;
import com.polling.poll.dto.comment.request.SaveCommentRequestDto;
import com.polling.poll.dto.comment.response.FindCommentResponseDto;
import com.polling.poll.service.CommentService;
import com.polling.queryrepository.CommentQueryRepository;
import com.polling.security.CurrentUser;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polls/candidates/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;
  private final CommentQueryRepository commentQueryRepository;

  @Trace
  @PostMapping()
  @ApiOperation(value = "특정 후보자에 응원 댓글 작성")
  public ResponseEntity<Void> saveComment(@CurrentUser MemberDto memberDto,
      @RequestBody SaveCommentRequestDto requestDto) {
    commentService.saveComment(requestDto, memberDto.getId());
    return ResponseEntity.status(200).build();
  }

  @GetMapping("/{candidateId}")
  @ApiOperation(value = "특정 후보자 응원 댓글 조회")
  public ResponseEntity<List<FindCommentResponseDto>> getComments(
      @PathVariable Long candidateId) {
    List<FindCommentResponseDto> responseDto = commentQueryRepository.findAllByCandidateId(
        candidateId);
    return ResponseEntity.status(200).body(responseDto);
  }

  @Trace
  @PutMapping("/{commentId}")
  @ApiOperation(value = "응원 댓글 수정")
  public ResponseEntity<Void> updateComment(@PathVariable Long commentId,
      @RequestBody ModifyCommentRequestDto requestDto) {
    commentService.changeContent(commentId, requestDto.getContent());
    return ResponseEntity.status(200).build();
  }

  @Trace
  @DeleteMapping("/{commentId}")
  @ApiOperation(value = "응원 댓글 삭제")
  public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
    commentService.deleteComment(commentId);
    return ResponseEntity.status(200).build();
  }
}
