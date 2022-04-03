package com.polling.contact.dto;

import javax.validation.constraints.NotNull;

import com.polling.contact.entity.Contact;
import com.polling.contact.entity.status.ContactType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * USER 회원의 문의 요청 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveContactRequestDto {

  @NotNull
  private String contactType;
  @NotNull
  private String title;
  @NotNull
  private String content;
  @NotNull
  private String email;

  public Contact toEntity() {
    ContactType ct = ContactType.findByMethod(contactType);
    return Contact.builder()
        .title(title)
        .content(content)
        .contactType(ct)
        .email(email)
        .build();
  }
}
