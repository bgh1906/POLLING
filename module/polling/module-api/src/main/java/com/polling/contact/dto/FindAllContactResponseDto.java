package com.polling.contact.dto;

import com.polling.contact.entity.status.ContactStatus;
import com.polling.contact.entity.status.ContactType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Contact Entity의 모든 정보를 반환하는 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAllContactResponseDto {

  private Long id;
  private ContactStatus contactStatus;
  private ContactType contactType;
  private String title;
  private String content;
  private Long memberId;
  private String answer;
  private String email;
}
