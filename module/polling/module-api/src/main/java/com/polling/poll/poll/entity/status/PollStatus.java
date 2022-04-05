package com.polling.poll.poll.entity.status;

import java.util.Arrays;

public enum PollStatus {
  UNAPPROVED("unapproved"),
  WAIT("wait"),
  IN_PROGRESS("progress"),
  DONE("done");
  private final String message;

  PollStatus(String message) {
    this.message = message;
  }

  public static PollStatus findStatusByName(String message) {
    return Arrays.stream(values()).filter(status -> status.getMessage().equals(message))
        .findFirst()
        .orElseThrow(RuntimeException::new);
  }

  public String getMessage() {
    return this.message;
  }
}
