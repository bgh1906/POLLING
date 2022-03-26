package com.polling.queryrepository;


import com.polling.poll.dto.comment.request.CommentDto;

import java.util.List;

public interface CommentQueryRepository {
    List<CommentDto> findAllByCandidateId(Long candidateId);
}