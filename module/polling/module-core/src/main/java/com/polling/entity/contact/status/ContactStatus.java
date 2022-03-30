package com.polling.entity.contact.status;

import com.polling.entity.member.status.MemberRole;
import com.polling.entity.poll.status.PollStatus;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ContactStatus {
  UNANSWERED("unanswered"),
  ANSWERED("answered");

  private String description;

  ContactStatus(String description) {
    this.description = description;
  }

  public static ContactStatus findByMethod(String description) {
    return Arrays.stream(values())
            .filter(m -> m.description.equals(description))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
  }

}

