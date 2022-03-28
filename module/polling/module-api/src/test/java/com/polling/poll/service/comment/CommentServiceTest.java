package com.polling.poll.service.comment;

import static org.assertj.core.api.Assertions.assertThat;

import com.polling.poll.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

  @InjectMocks
  private CommentService target;

  @Test
  public void commentServiceIsNotNull() throws Exception {
    assertThat(target).isNotNull();
  }

}
