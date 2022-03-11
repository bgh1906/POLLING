package com.ssafy.core.repository.comment;

import com.ssafy.core.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
