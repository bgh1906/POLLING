package com.polling.queryrepository;


import com.polling.candidate.dto.CommentDto;

import java.util.List;

public interface CommentQueryRepository {
    List<CommentDto> findCommentByCandidateId(Long id);
}