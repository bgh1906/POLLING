package com.polling.contact.dto;

import com.polling.entity.contact.Contact;
import com.polling.entity.contact.status.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * USER 회원의 문의 요청 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveContactRequestDto {

  private ContactType contactType;
  private String title;
  private String content;

  public Contact toEntity(){
    return Contact.builder()
        .title(title)
        .content(content)
        .contactType(contactType)
        .build();
  }
}
