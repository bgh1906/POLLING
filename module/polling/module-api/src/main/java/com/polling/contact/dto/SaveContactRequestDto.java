package com.polling.contact.dto;

import com.polling.entity.contact.status.ContactType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveContactRequestDto {

  private ContactType contactType;
  private String title;
  private String content;

  @Builder
  public SaveContactRequestDto(ContactType contactType, String title, String content) {
    this.contactType = contactType;
    this.title = title;
    this.content = content;
  }
}
