package com.polling.contact.entity.status;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ContactType {
  CONTACT_COMPANY("기업"),
  CONTACT_POLL("투표"),
  CONTACT_USER("회원"),
  CONTACT_NFT("NFT"),
  CONTACT_OTHER("기타");

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

