package com.polling.poll.comment.repository;


import com.polling.poll.dto.comment.response.FindCommentResponseDto;
import java.util.List;

public interface CommentQueryRepository {

  List<FindCommentResponseDto> findAllByCandidateId(Long candidateId);
}