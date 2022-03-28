package com.polling.queryrepository;


import com.polling.poll.dto.comment.response.FindCommentResponseDto;

import java.util.List;

public interface CommentQueryRepository {
    List<FindCommentResponseDto> findAllByCandidateId(Long candidateId);
}