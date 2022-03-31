package com.polling.contact.dto;

import com.polling.entity.contact.status.ContactStatus;
import com.polling.entity.contact.status.ContactType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 특정 회원의 FAQ 정보를 반환하는 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindContactResponseDto {

  private Long id;
  private ContactStatus contactStatus;
  private ContactType contactType;
  private String title;
  private String content;
  private String answer;
}
