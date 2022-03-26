package com.polling.poll.controller;

import com.polling.auth.dto.MemberDto;
import com.polling.poll.dto.candidate.request.ModifyCommentRequestDto;
import com.polling.poll.dto.candidate.request.SaveCommentRequestDto;
import com.polling.poll.service.CommentService;
import com.polling.security.CurrentUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polls/candidates/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    @ApiOperation(value = "특정 후보자에 응원 댓글 작성")
    public ResponseEntity<Void> saveComment(@CurrentUser MemberDto memberDto, @RequestBody SaveCommentRequestDto requestDto) {
        commentService.saveComment(requestDto, memberDto.getId());
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{commentId}")
    @ApiOperation(value = "응원 댓글 수정")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId, @RequestBody ModifyCommentRequestDto requestDto) {
        commentService.updateComment(commentId, requestDto);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{commentId}")
    @ApiOperation(value = "응원 댓글 삭제")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(200).build();
    }
}
