package com.polling.entity.contact.status;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ContactType {
  COMPANY("company"),
  POLL("poll"),
  TICKET("ticket");

  private String description;

  ContactType(String description) {
    this.description = description;
  }

  public static ContactType findByMethod(String description) {
    return Arrays.stream(values())
        .filter(m -> m.description.equals(description))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  public String getDescription() {
    return this.description;
  }
}

