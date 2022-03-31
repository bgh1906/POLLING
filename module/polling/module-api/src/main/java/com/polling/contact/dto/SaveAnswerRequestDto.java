package com.polling.contact.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ADMIN 회원의 답변 등록 요청 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveAnswerRequestDto {

  @NotNull
  private Long contactId;
  @NotNull
  private String answer;
}
