package com.polling.contact.entity.status;

import java.util.Arrays;
import lombok.Getter;

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

